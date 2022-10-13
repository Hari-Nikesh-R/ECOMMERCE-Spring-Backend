package com.example.ECommerceMerchantCatalog.repository;

import com.example.ECommerceMerchantCatalog.model.MerchantProfileDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MerchantTestRepository extends MongoRepository<MerchantProfileDto,Integer> {

}
