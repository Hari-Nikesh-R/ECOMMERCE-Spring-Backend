package com.example.ECommerceMerchantCatalog.service;

import com.example.ECommerceMerchantCatalog.model.MerchantProfile;
import com.example.ECommerceMerchantCatalog.model.MerchantProfileDto;
import com.example.ECommerceMerchantCatalog.model.ProductDto;

import java.util.List;

public interface MerchantCatalogService {
    List<ProductDto> getMyProducts(int merId);
    ProductDto openProduct(int merId,int id);
    String addItems(ProductDto productDto,int id);
    String deleteItems(int merId,int productId);
    MerchantProfile save(MerchantProfile merchantProfile);
    String updateStock(int merId,int productId,int stock);

    String updateProduct(int merId,int productId,ProductDto productDto);




}
