package com.example.cartService.service;

import com.example.cartService.entity.Cart;
import com.example.cartService.entity.CartDto;
import com.example.cartService.entity.ProductCatalogModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    public List<ProductCatalogModel> findCartById(int userId);
    public Cart saveCart(CartDto cart);
    String deleteCartItem(CartDto cartDto);
    ProductCatalogModel setQuantity(CartDto cartDto);
    ProductCatalogModel placedOrder(CartDto cartDto);
    String placeAllOrder(int userId);
    String deleteItem(CartDto cartDto);
}
