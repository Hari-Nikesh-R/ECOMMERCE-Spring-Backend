package com.example.ECommerceMerchantCatalog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import java.util.List;

@Document
public class MerchantProfile{

    private int id;
    private List<ProductDto> productItems;
    private int rating;
    private int itemsSold;

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ProductDto> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductDto> productItems) {
        this.productItems = productItems;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getItemsSold() {
        return itemsSold;
    }

    public void setItemsSold(int itemsSold) {
        this.itemsSold = itemsSold;
    }

    @Override
    public String toString() {
        return "MerchantProfile{" +
                "id=" + id +
                ", rating=" + rating +
                ", itemsSold=" + itemsSold +
                '}';
    }
}
