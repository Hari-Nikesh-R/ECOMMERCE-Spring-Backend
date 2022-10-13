package com.example.ECommerceUserCatalog.service;

import com.example.ECommerceUserCatalog.model.ProductCatalogModel;
import com.example.ECommerceUserCatalog.model.RatingDto;
import com.example.ECommerceUserCatalog.model.UserProfileModel;

import java.util.List;
import java.util.Optional;

public interface UserCatalogService {
    List<ProductCatalogModel> getAllProduct();
    ProductCatalogModel getProduct(int id);
    Double getReview(String name);
    ProductCatalogModel save(ProductCatalogModel productCatalogModel);
    List<ProductCatalogModel> getNewArrivals();
   UserProfileModel getUserProfile(int userId);
    void deleteByProduct(int id);
    List<ProductCatalogModel> getByBrand(String brand);
    UserProfileModel updateProfile(UserProfileModel userProfileModel);
    List<ProductCatalogModel> getByCategory(String category);
    ProductCatalogModel setRating(RatingDto ratingDto);
    ProductCatalogModel stockSave(ProductCatalogModel productCatalogModel);


}
