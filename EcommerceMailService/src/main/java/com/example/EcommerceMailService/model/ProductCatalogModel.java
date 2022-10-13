package com.example.EcommerceMailService.model;

import java.util.Date;
import java.util.List;

public class ProductCatalogModel {
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private Date timeStamp;

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    private String title;

    private String description;

    private String category;

    private int price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return   "title='" + title+"\n"+
                "quantity=" + quantity + "\n"+
                ", timeStamp=" + timeStamp + "\n"+
                ", description='" + description + "\n" +
                ", category='" + category + "\n" +
                ", price=" + price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
