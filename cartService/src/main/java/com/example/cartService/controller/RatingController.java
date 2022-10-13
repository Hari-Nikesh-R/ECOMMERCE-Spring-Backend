package com.example.cartService.controller;


import com.example.cartService.entity.ProductCatalogModel;
import com.example.cartService.entity.RatingModel;
import com.example.cartService.entity.Review;
import com.example.cartService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/review")
public class RatingController {
    @Autowired
    RatingService reviewService;

    @GetMapping(value="/{product_id}")
    public ProductCatalogModel getReviewsByProductId(@PathVariable("product_id") int product_id) {
        return reviewService.getRatingByProductId(product_id);
    }

    @PostMapping
    public String saveRating(@RequestBody RatingModel ratingModel) {
        return reviewService.saveRating(ratingModel);
    }

//    @DeleteMapping(value="/{review_id}")
//    public void deleteReviewByReviewId(@PathVariable("review_id") int review_id) {
//        reviewService.deleteRatingByRatingId(review_id);
//    }
}
