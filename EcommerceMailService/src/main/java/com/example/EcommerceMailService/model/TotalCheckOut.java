package com.example.EcommerceMailService.model;

import java.util.List;

public class TotalCheckOut {
    private int totalBill;
    private int userId;
    private List<ProductCheckOut> products;

    public int getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
    }

    public List<ProductCheckOut> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCheckOut> products) {
        this.products = products;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
