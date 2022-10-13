package com.example.cartService.service;

import com.example.cartService.entity.ProductCatalogModel;
import com.example.cartService.entity.RatingModel;
import com.example.cartService.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    public ProductCatalogModel getRatingByProductId(int product_id);
    public String saveRating(RatingModel ratingModel);
   // void deleteRatingByRatingId(Integer reviewId);

}
