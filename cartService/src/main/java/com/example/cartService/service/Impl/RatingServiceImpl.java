package com.example.cartService.service.Impl;

import com.example.cartService.entity.ProductCatalogModel;
import com.example.cartService.entity.RatingModel;
import com.example.cartService.entity.Review;
import com.example.cartService.repository.RatingServiceRepository;
import com.example.cartService.service.RatingService;
import com.example.cartService.urls.Urls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingServiceRepository ratingServiceRepository;

    @Override
    public ProductCatalogModel getRatingByProductId(int product_id) {
        RestTemplate restTemplate = new RestTemplate();
        Optional<RatingModel> optionalRatingService = ratingServiceRepository.getRatingByProductId(product_id);
        if(optionalRatingService.isPresent()) {
            ProductCatalogModel productCatalogModel = restTemplate.getForObject(Urls.product + "catalog/" + product_id, ProductCatalogModel.class);
            productCatalogModel.setRating(optionalRatingService.get().getRating());
            return productCatalogModel;
        }
        return null;

    }

    @Override
    public String saveRating(RatingModel ratingModel) {
        RestTemplate restTemplate = new RestTemplate();
        if(ratingModel!=null) {
            ProductCatalogModel productCatalogModel = restTemplate.getForObject(Urls.product+"catalog/"+ratingModel.getProductId(),ProductCatalogModel.class);
            productCatalogModel.setRating(ratingModel.getRating());
            ratingServiceRepository.save(ratingModel);
            productCatalogModel.setRating(ratingServiceRepository.getAverageRating(ratingModel.getProductId()));
            restTemplate.postForObject(Urls.product,productCatalogModel,ProductCatalogModel.class);

            return "RATED SUCCESSFULLY";
        }
        return "FAILED TO RATE";
    }

//    @Override
//    public void deleteRatingByRatingId(Integer ratingId) {
//        Optional<Review> optionalReview = ratingServiceRepository.findById(ratingId);
//        if(optionalReview.isPresent()) {
//            ratingServiceRepository.delete(optionalReview.get());
//        }
//    }
}
