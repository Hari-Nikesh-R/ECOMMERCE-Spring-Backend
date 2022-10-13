package com.example.ECommerceUserCatalog.service.impl;

import com.example.ECommerceUserCatalog.model.ProductCatalogModel;
import com.example.ECommerceUserCatalog.model.RatingDto;
import com.example.ECommerceUserCatalog.model.UserProfileModel;
import com.example.ECommerceUserCatalog.repository.UserCatalogRepository;
import com.example.ECommerceUserCatalog.service.UserCatalogService;
import com.example.ECommerceUserCatalog.urls.Urls;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserCatalogServiceImpl implements UserCatalogService {


    @Autowired
    UserCatalogRepository userCatalogRepository;



// UserProfileRepository userProfileRepository;


    @Override
    public List<ProductCatalogModel> getAllProduct() {
        List<ProductCatalogModel> result = new ArrayList<>();
        userCatalogRepository.findAll().forEach(result::add);
        return result;

    }

    @Override
    public ProductCatalogModel getProduct(int id) {
        Optional<ProductCatalogModel> optionalProductCatalogModel = userCatalogRepository.findById(id);
        return optionalProductCatalogModel.isPresent()?optionalProductCatalogModel.get():null;
    }

    @Override
    public Double getReview(String title) {
        Optional<ProductCatalogModel> optionalProductCatalogModel = userCatalogRepository.findByTitle(title);
        return optionalProductCatalogModel.isPresent()?optionalProductCatalogModel.get().getRating():null;
    }

    @Override
    public ProductCatalogModel save(ProductCatalogModel productCatalogModel){
        int size = userCatalogRepository.findAll().size();
        if(size==0)
        {
            productCatalogModel.setId(0);
        }
        else if(size>1){
            int nextId=0;
            int indexId = size-1;
            if(userCatalogRepository.findAll().get(indexId).getId() < userCatalogRepository.findAll().get(indexId-1).getId()){
                List<Integer> toFindMax = new ArrayList<>();
                for(int index=0;index<=indexId;index++)
                {
                    toFindMax.add(userCatalogRepository.findAll().get(index).getId());
                }
                nextId = Collections.max(toFindMax);
                productCatalogModel.setId(nextId+1);

            }
            else {
                productCatalogModel.setId(userCatalogRepository.findAll().get(userCatalogRepository.findAll().size() - 1).getId() + 1);
            }

        }
        else
        {
            productCatalogModel.setId(1);
        }

        // updated here
        productCatalogModel.setBrand(productCatalogModel.getBrand().toLowerCase());
        productCatalogModel.setTimeStamp(new Date());
        return userCatalogRepository.save(productCatalogModel);
    }

    @Override
    public List<ProductCatalogModel> getNewArrivals() {
        //todo:add time stamp
        List<ProductCatalogModel> result = new ArrayList<>();
        userCatalogRepository.findAll().forEach(result::add);
        Collections.reverse(result);
        List<ProductCatalogModel> newArrivals = result.stream().limit(10).collect(Collectors.toList());
        return newArrivals;
    }

    @Override
    public UserProfileModel getUserProfile(int userId){
        RestTemplate restTemplate = new RestTemplate();
        UserProfileModel userProfileModel = restTemplate.getForObject(Urls.auth+"users/"+userId,UserProfileModel.class);
       // Optional<UserProfileModel> optionalUserProfileDto = userProfileRepository.findByUserName(userId);
        if(userProfileModel!=null)
        {
            return userProfileModel;
        }
        System.out.println("came to null");
        return null;

    }

    @Override
    public void deleteByProduct(int id) {
        Optional<ProductCatalogModel> optionalProductCatalogModel = userCatalogRepository.findById(id);
        if(optionalProductCatalogModel.isPresent()) {
            userCatalogRepository.delete(optionalProductCatalogModel.get());
            System.out.println("Product has deleted");
        }
    }

    @Override
    public List<ProductCatalogModel> getByBrand(String brand) {
        List<ProductCatalogModel> optionalProductCatalogModel = userCatalogRepository.findByBrand(brand);
        return optionalProductCatalogModel;


    }
    @Override
    public UserProfileModel updateProfile(UserProfileModel userProfileModel) {
        String decoded ="";
        RestTemplate restTemplate = new RestTemplate();
        UserProfileModel userProfile = restTemplate.getForObject(Urls.auth+"users/"+userProfileModel.getId(),UserProfileModel.class);

//        for(int index=0;index<userProfileModel.getPassword().length();index++)
//        {
//            decoded += (char)(((userProfileModel.getPassword().charAt((index)) - 32)+126)/2);
//        }
//        System.out.println(decoded);
        String username = userProfile.getUserName();
        String password = userProfile.getPassword();
        int id = userProfile.getId();
        BeanUtils.copyProperties(userProfileModel,userProfile);
      //  userProfile.setPassword(decoded);
        userProfile.setId(id);
        userProfile.setUserName(username);
        userProfile.setPassword(password);
     //   userProfile.setUserName(userProfileModel.getUserName());
        UserProfileModel response = restTemplate.postForObject(Urls.auth+"profile/update",userProfile,UserProfileModel.class);
        return response;


    }

    @Override
    public List<ProductCatalogModel> getByCategory(String category) {
        List<ProductCatalogModel> optionalProductCatalogModel = userCatalogRepository.findByCategory(category);
        return optionalProductCatalogModel;


    }

    @Override
    public ProductCatalogModel setRating(RatingDto ratingDto) {
        ProductCatalogModel productCatalogModel = getProduct(ratingDto.getProductId());
        productCatalogModel.setRating(ratingDto.getRating()/RatingDto.getRatingCount());

        return userCatalogRepository.save(productCatalogModel);
    }

    @Override
    public ProductCatalogModel stockSave(ProductCatalogModel productCatalogModel) {
        deleteByProduct(productCatalogModel.getId());
       return userCatalogRepository.save(productCatalogModel);
    }

}
