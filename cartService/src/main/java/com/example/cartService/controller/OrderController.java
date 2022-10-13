package com.example.cartService.controller;


import com.example.cartService.entity.Order;
import com.example.cartService.entity.ProductCatalogModel;
import com.example.cartService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping
    public Order saveOrder(@RequestBody Order order)
    {
        return orderService.saveOrder(order);
    }

    @GetMapping(value = "/details/{userId}")
    public List<ProductCatalogModel> orderDetails(@PathVariable("userId") int userId)
    {
        return orderService.getOrderHistory(userId);
    }




}
