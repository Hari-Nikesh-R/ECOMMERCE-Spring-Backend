package com.example.ECommerceMerchantCatalog.service.impl;

import com.example.ECommerceMerchantCatalog.model.CartDto;
import com.example.ECommerceMerchantCatalog.model.MerchantProfile;

import com.example.ECommerceMerchantCatalog.model.MerchantProfileDto;
import com.example.ECommerceMerchantCatalog.model.ProductDto;
import com.example.ECommerceMerchantCatalog.repository.MerchantCatalogRepository;
import com.example.ECommerceMerchantCatalog.repository.MerchantTestRepository;
import com.example.ECommerceMerchantCatalog.service.MerchantCatalogService;
import com.example.ECommerceMerchantCatalog.urls.Urls;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class MerchantCatalogServiceImpl implements MerchantCatalogService {

    @Autowired
    MerchantCatalogRepository merchantCatalogRepository;

    @Autowired
    MerchantTestRepository merchantTestRepository;

    @Override
    public List<ProductDto> getMyProducts(int merId) {
       // List<ProductDto> result = new ArrayList<>();
        Optional<MerchantProfile> optionalProductDto = merchantCatalogRepository.findById(merId);
//        result = optionalProductDto.get().getProductItems();
//        return result;
        return optionalProductDto.isPresent()?optionalProductDto.get().getProductItems():null;

    }

    @Override
    public ProductDto openProduct(int merId,int id) {
        Optional<MerchantProfile> optionalMerchantProfile = merchantCatalogRepository.findById(id);
        if(optionalMerchantProfile.isPresent())
        {
            List<ProductDto> productDtoList = optionalMerchantProfile.get().getProductItems();
            for(int index=0;index<productDtoList.size();index++)
            {
                if(productDtoList.get(index).getId()==id)
                {
                    return productDtoList.get(index);
                }
            }

        }
        return null;
    }

    @Override
    public String addItems(ProductDto productDto, int id) {
        RestTemplate restTemplate = new RestTemplate();
        MerchantProfileDto merchantProfileDto = restTemplate.getForObject(Urls.auth+"merchant/"+id,MerchantProfileDto.class);
      //  Optional<MerchantProfile> optionalMerchantProfile = merchantCatalogRepository.findById(id);

        System.out.println("printing ID"+id);
        MerchantProfile merchantProfile = new MerchantProfile();
            if(merchantProfileDto.getId() == id){
            productDto.setMerchant_id(id);
          //  productDto.setImage(productDto.getImage().get(0).trim());
            ProductDto product =  restTemplate.postForObject(Urls.product,productDto,ProductDto.class);

            ProductDto elasticProduct = restTemplate.postForObject(Urls.elastic+"save",product,ProductDto.class);
            System.out.println(elasticProduct.toString());
            Date date = new Date();
            productDto.setTimeStamp(date);

            merchantProfile.setId(merchantProfileDto.getId());
            Optional<MerchantProfile> optionalMerchantProfile = merchantCatalogRepository.findById(id);

            if(optionalMerchantProfile.isPresent()) {
                System.out.println("printing ID" + optionalMerchantProfile.get().getId());
                productDto.setId(product.getId());
                productDto.setMerchant_id(id);
                optionalMerchantProfile.get().getProductItems().add(productDto);
                merchantProfile.setProductItems(optionalMerchantProfile.get().getProductItems());
                merchantCatalogRepository.save(merchantProfile);

            }
            else {
                List<ProductDto> productList = new ArrayList<>();
                productDto.setId(product.getId());
                productList.add(productDto);
                merchantProfile.setProductItems(productList);
                merchantCatalogRepository.save(merchantProfile);
            }

            return "added successfully";
        }

        return "Failed";
    }

    @Override
    public String deleteItems(int merId,int productId) {
        RestTemplate restTemplate = new RestTemplate();
        Optional<MerchantProfile> optionalMerchantProfile = merchantCatalogRepository.findById(merId);
        if(optionalMerchantProfile.isPresent())
        {
            List<ProductDto> productToBeDeleted = optionalMerchantProfile.get().getProductItems();
            for(int index=0;index<productToBeDeleted.size();index++)
            {
                if(productToBeDeleted.get(index).getId() == productId)
                {
                   // restTemplate.delete(Urls.product+"catalog/product/"+productId);
                    optionalMerchantProfile.get().getProductItems().remove(index);
                    // changed here
                    merchantCatalogRepository.save(optionalMerchantProfile.get());
                    try {
                        restTemplate.delete(Urls.cart + "delete/" + productToBeDeleted.get(index).getId());
                        restTemplate.delete(Urls.elastic+"delete/"+productId);
                        restTemplate.delete((Urls.product+"catalog/product/"+productId));
                        return "removed Succesfully";
                    }
                    catch(Exception e){
                        restTemplate.delete(Urls.elastic+"delete/"+productId);
                        restTemplate.delete((Urls.product+"catalog/product/"+productId));
                        return "removed Succesfully";

                    }

                }
            }
        }
        return "removing unsuccessful";
    }

    @Override
    public MerchantProfile save(MerchantProfile merchantProfile) {
        return merchantCatalogRepository.save(merchantProfile);
    }

    @Override
    public String updateStock(int merchantId,int productId, int stock) {
        RestTemplate restTemplate = new RestTemplate();
        ProductDto productDto = restTemplate.getForObject(Urls.product+"catalog/"+productId,ProductDto.class);
       // productDto.setStock(stock);
        Optional<MerchantProfile> optionalMerchantProfile = merchantCatalogRepository.findById(merchantId);
        if(optionalMerchantProfile.isPresent())
        {
            for(int product=0;product<optionalMerchantProfile.get().getProductItems().size();product++)
            {
                if(optionalMerchantProfile.get().getProductItems().get(product).getId() == productId)
                {
                    optionalMerchantProfile.get().getProductItems().get(product).setStock(stock);
                    merchantCatalogRepository.save(optionalMerchantProfile.get());
                    productDto.setStock(stock);
                   // productDto.setStock(stock);
                   // restTemplate.delete(Urls.product+"catalog/product/"+productDto.getId());
                    restTemplate.postForObject(Urls.product+"update/stock",productDto,ProductDto.class);
                    return "Stock is updated";
                }

            }

        }

      //  addItems(productDto,productDto.getMerchant_id());
        return "ERROR TRY AGAIN LATER";

    }

    @Override
    public String updateProduct(int merId,int productId,ProductDto productDto) {
        RestTemplate restTemplate = new RestTemplate();
        ProductDto product = restTemplate.getForObject(Urls.product+"catalog/"+productId,ProductDto.class);
        Optional<MerchantProfile> optionalMerchantProfile = merchantCatalogRepository.findById(merId);
        if(optionalMerchantProfile.isPresent())
        {
            List<ProductDto> productDtoList = optionalMerchantProfile.get().getProductItems();
            for(int index=0;index<productDtoList.size();index++)
            {
                if(productDtoList.get(index).getId() == productId)
                {
                    productDto.setId(productId);
                    productDto.setMerchant_id(merId);
                    BeanUtils.copyProperties(productDto,productDtoList.get(index));
                    restTemplate.postForObject(Urls.product+"update/product",productDto,ProductDto.class);
                    restTemplate.postForObject(Urls.elastic+"save",productDto,ProductDto.class);
                    return "Product Updated successfully";
                }
            }

        }
        return "Updating Unsuccessful";


    }


}
