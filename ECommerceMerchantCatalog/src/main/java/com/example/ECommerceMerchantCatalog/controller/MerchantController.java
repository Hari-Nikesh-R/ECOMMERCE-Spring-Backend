package com.example.ECommerceMerchantCatalog.controller;

import com.example.ECommerceMerchantCatalog.model.MerchantProfile;
import com.example.ECommerceMerchantCatalog.model.ProductDto;
import com.example.ECommerceMerchantCatalog.service.MerchantCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/merchant")
public class MerchantController {

    @Autowired
    MerchantCatalogService merchantCatalogService;


    @GetMapping(value = "/catalog/{merId}")
    public List<ProductDto> showMyProduct(@PathVariable("merId") int merId)
    {
        return merchantCatalogService.getMyProducts(merId);
    }

    @GetMapping(value = "/catalog/{merId}/{productId}")
    public ProductDto myProducts(@PathVariable("mer_id") int merId,@PathVariable("id") int id)
    {
        return merchantCatalogService.openProduct(merId,id);
    }

    @PostMapping(value = "/{merId}/add")
    public String addProduct(@RequestBody ProductDto productDto,@PathVariable int merId)
    {
        return merchantCatalogService.addItems(productDto,merId);
    }


    @PostMapping(value = "/{merId}/delete/{productId}")
    @CrossOrigin("*")
    public String deleteItems(@PathVariable("merId") int merId,@PathVariable("productId") int productId)
    {
        return merchantCatalogService.deleteItems(merId,productId);
    }

    @PostMapping
    public MerchantProfile save(@RequestBody MerchantProfile merchantProfile)
    {
        return merchantCatalogService.save(merchantProfile);
    }

    @GetMapping(value = "/{merId}/update/{productId}/{stock}")
    public String updateProductStock(@PathVariable int merId,@PathVariable int productId,@PathVariable int stock)
    {
        return merchantCatalogService.updateStock(merId,productId,stock);
    }

    @PostMapping(value = "/{merId}/{productId}/edit")
    public String updateProduct(@PathVariable("merId") int merId,@PathVariable("productId") int productId,@RequestBody ProductDto productDto)
    {
        return merchantCatalogService.updateProduct(merId,productId,productDto);
    }

}
