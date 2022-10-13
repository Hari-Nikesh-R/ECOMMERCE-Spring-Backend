package com.example.ECommerceAuthService.service;

import com.example.ECommerceAuthService.model.MerchantLoginAuth;
import com.example.ECommerceAuthService.model.UserLoginAuth;
import com.example.ECommerceAuthService.model.UserProfileModel;


public interface UserAuthService  {
    public UserProfileModel checkValidity(int id);
    public MerchantLoginAuth checkMerchantValidity(int id);
    public UserLoginAuth validationMerchantPass(UserLoginAuth userLoginAuth);
    public MerchantLoginAuth merchantSave(MerchantLoginAuth merchantLoginAuth);
    public UserProfileModel save(UserProfileModel userProfileModel);
    UserLoginAuth validationUserPass(UserLoginAuth userLoginAuth);
    UserProfileModel update(UserProfileModel userProfileModel);


}
