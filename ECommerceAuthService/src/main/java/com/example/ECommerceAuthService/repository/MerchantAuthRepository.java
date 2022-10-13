package com.example.ECommerceAuthService.repository;

import com.example.ECommerceAuthService.model.MerchantLoginAuth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantAuthRepository extends CrudRepository<MerchantLoginAuth,Integer> {
    Optional<MerchantLoginAuth> findByUserName(String userName);

}
