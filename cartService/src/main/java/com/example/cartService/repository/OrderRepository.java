package com.example.cartService.repository;

import com.example.cartService.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {
    @Query(value = "select count(user_id) from order where product_id=?1",nativeQuery = true)
    int getRatingCount(int productId);


    List<Order> findByUserId(int userId);



}
