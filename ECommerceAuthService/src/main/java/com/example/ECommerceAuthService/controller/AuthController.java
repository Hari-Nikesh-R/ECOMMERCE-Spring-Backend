package com.example.ECommerceAuthService.controller;

import com.example.ECommerceAuthService.model.MerchantLoginAuth;
import com.example.ECommerceAuthService.model.UserLoginAuth;
import com.example.ECommerceAuthService.model.UserProfileModel;
import com.example.ECommerceAuthService.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/login")
public class AuthController{

    @Autowired
    UserAuthService userAuthService;


    @GetMapping(value = "/users/{id}")
    public UserProfileModel getUsername(@PathVariable("id") int id) {
        return userAuthService.checkValidity(id);
    }


    @GetMapping(value = "/merchant/{id}")
    public MerchantLoginAuth getMerchantPass(@PathVariable("id") int id) {

        return userAuthService.checkMerchantValidity(id);
    }

    @PostMapping(value = "/validate/merchant")
    public UserLoginAuth validateMerchantPass(@RequestBody UserLoginAuth userLoginAuth)
    {
        return userAuthService.validationMerchantPass(userLoginAuth);
    }


    @PostMapping(value = "/register/merchant")
    public MerchantLoginAuth merchantSave(@RequestBody MerchantLoginAuth merchantLoginAuth)
    {

        return userAuthService.merchantSave(merchantLoginAuth);

    }
    @PostMapping(value = "/validate/user")
    public UserLoginAuth validateUserPass(@RequestBody UserLoginAuth userLoginAuth)
    {
        return userAuthService.validationUserPass(userLoginAuth);
    }

    @PostMapping(value = "/register")
    public UserProfileModel save(@RequestBody UserProfileModel userProfileModel)
    {
        return userAuthService.save(userProfileModel);
    }

    @PostMapping(value = "/profile/update")
    public UserProfileModel updateProfile(@RequestBody UserProfileModel userProfileModel)
    {
        return userAuthService.update(userProfileModel);
    }
}
