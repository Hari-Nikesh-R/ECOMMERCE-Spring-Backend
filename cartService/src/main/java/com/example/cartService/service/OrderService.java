package com.example.cartService.service;

import com.example.cartService.entity.Order;
import com.example.cartService.entity.OrderDto;
import com.example.cartService.entity.ProductCatalogModel;

import java.util.List;

public interface OrderService {
    Order saveOrder(Order order);

    List<ProductCatalogModel> getOrderHistory(int userId);
    int getProductRatingCount(int productId);

}
