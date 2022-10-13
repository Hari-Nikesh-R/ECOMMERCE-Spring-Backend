package com.example.cartService.repository;

import com.example.cartService.entity.Cart;
import com.example.cartService.entity.CartDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartServiceRepository extends CrudRepository<Cart, Integer> {

    @Query(value = "select * from cart where user_id = ?1",nativeQuery = true)
     List<Cart> findByUserId(int userId);

    @Query(value = "delete from cart where product_id = ?1",nativeQuery = true)
    void deleteByProductId(int productId);

    @Query(value = "select * from cart where user_id = ?1 and product_id = ?2",nativeQuery = true)
    Optional<Cart> findByUserIdANDProductId(int userId, int productId);

    @Query(value = "update cart set quantity = ?3 where user_id = ?1 and product_id = ?2",nativeQuery = true)
    Optional<Cart> updateCart(int userId,int productId,int quantity);

   // void deleteCartByCartId(int cart_id);
}
