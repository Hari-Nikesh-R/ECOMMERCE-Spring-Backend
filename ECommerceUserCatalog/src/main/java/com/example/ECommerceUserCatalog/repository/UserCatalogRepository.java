package com.example.ECommerceUserCatalog.repository;

import com.example.ECommerceUserCatalog.model.ProductCatalogModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCatalogRepository extends MongoRepository<ProductCatalogModel,Integer> {
 //   @Query("{title :?0}")
    Optional<ProductCatalogModel> findByTitle(String productName);

   // @Query("{brand :?0}")
    List<ProductCatalogModel> findByBrand(String brand);


    List<ProductCatalogModel> findByCategory(String category);
}
