package com.example.cartService.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDto {

    private int userId;
    private int productId;
    private int quantity;
    private int price;
    private int cartId;
    private int merchantId;

    private List<Order> orderList;


}
