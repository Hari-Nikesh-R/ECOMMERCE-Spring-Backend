package com.example.cartService.repository;

import com.example.cartService.entity.ProductCatalogModel;
import com.example.cartService.entity.RatingModel;

import com.example.cartService.service.RatingService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingServiceRepository extends CrudRepository<RatingModel, Integer> {

    @Query(value = "select rating from rating_model where product_id=?1",nativeQuery = true)
    Optional<RatingModel> getRatingByProductId(int productId);

    @Query(value = "select avg(rating) from rating_model where product_id=?1",nativeQuery = true)
    double getAverageRating(int productId);



}
