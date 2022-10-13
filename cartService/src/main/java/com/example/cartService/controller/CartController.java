package com.example.cartService.controller;


import com.example.cartService.entity.Cart;
import com.example.cartService.entity.CartDto;
import com.example.cartService.entity.ProductCatalogModel;
import com.example.cartService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping(value="/{userId}")
    public List<ProductCatalogModel> findByUserId(@PathVariable("userId") int id){
        return cartService.findCartById(id);
    }

    @PostMapping
    public Cart saveCart(@RequestBody CartDto cartDto){
        return cartService.saveCart(cartDto);
    }

    @PostMapping("/quantity")
    public ProductCatalogModel setQuantity(@RequestBody CartDto cartDto)
    {
        return cartService.setQuantity(cartDto);
    }

    @DeleteMapping(value = "/delete")
    public String deleteCartByCartId(@RequestBody CartDto cartDto){
        return cartService.deleteCartItem(cartDto);
    }

    @PostMapping(value = "/placed")
    public ProductCatalogModel orderPlaced(@RequestBody CartDto cartDto)
    {
        return cartService.placedOrder(cartDto);
    }


    @PostMapping(value = "/placedall/{userId}")
    public String orderAllPlaced(@PathVariable("userId") int userId)
    {
        return cartService.placeAllOrder(userId);
    }

    @PostMapping(value = "/delete/android")
    public String deleteCartItem(@RequestBody CartDto cartDto){
       return cartService.deleteItem(cartDto);
    }





}
