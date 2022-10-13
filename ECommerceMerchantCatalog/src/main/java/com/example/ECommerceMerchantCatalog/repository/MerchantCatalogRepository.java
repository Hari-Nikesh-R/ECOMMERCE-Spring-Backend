package com.example.ECommerceMerchantCatalog.repository;

import com.example.ECommerceMerchantCatalog.model.MerchantProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantCatalogRepository extends MongoRepository<MerchantProfile,Integer> {

}
