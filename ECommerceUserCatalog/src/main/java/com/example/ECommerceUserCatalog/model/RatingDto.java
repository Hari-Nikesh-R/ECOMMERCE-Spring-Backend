package com.example.ECommerceUserCatalog.model;

public class RatingDto {
    static int ratingCount=1;

    public static int getRatingCount() {
        return ratingCount;
    }

    public static void setRatingCount(int ratingCount) {
        RatingDto.ratingCount = ratingCount;
    }

    private int userId;
    private int rating;
    private int productId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
