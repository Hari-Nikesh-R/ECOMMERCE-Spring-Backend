package com.example.ECommerceUserCatalog.controller;


import com.example.ECommerceUserCatalog.model.ProductCatalogModel;
import com.example.ECommerceUserCatalog.model.UserProfileModel;
import com.example.ECommerceUserCatalog.service.UserCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/User")
public class UserCatalogController {

    @Autowired
    UserCatalogService userCatalogService;


        @GetMapping(value = "/profile/{userId}")
    public UserProfileModel getUser(@PathVariable("userId") int userId)
    {

        return userCatalogService.getUserProfile(userId);
    }

    @PostMapping(value = "/update")
    public UserProfileModel update(@RequestBody UserProfileModel userProfileModel)
    {
        return userCatalogService.updateProfile(userProfileModel);
    }




}
