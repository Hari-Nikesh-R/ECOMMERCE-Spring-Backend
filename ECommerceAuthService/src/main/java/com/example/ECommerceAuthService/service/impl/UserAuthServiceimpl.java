package com.example.ECommerceAuthService.service.impl;

import com.example.ECommerceAuthService.model.MerchantLoginAuth;
import com.example.ECommerceAuthService.model.UserLoginAuth;
import com.example.ECommerceAuthService.model.UserProfileModel;
import com.example.ECommerceAuthService.repository.MerchantAuthRepository;
import com.example.ECommerceAuthService.repository.UserProfileRepository;
import com.example.ECommerceAuthService.service.UserAuthService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserAuthServiceimpl implements UserAuthService {

    @Autowired
    MerchantAuthRepository merchantAuthRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public UserProfileModel checkValidity(int id) {
        Optional<UserProfileModel> optionalUserLoginAuth = userProfileRepository.findById(id);
        return optionalUserLoginAuth.isPresent() ? optionalUserLoginAuth.get() : null;
    }

    @Override
    public MerchantLoginAuth checkMerchantValidity(int id) {
        Optional<MerchantLoginAuth> optionalMerchantLoginAuth = merchantAuthRepository.findById(id);
        if (optionalMerchantLoginAuth.isPresent()) {
                return optionalMerchantLoginAuth.get();
        }
        return null;
    }

    @Override
    public UserLoginAuth validationMerchantPass(UserLoginAuth userLoginAuth) {
        String decoded ="";
        for(int index=0;index<userLoginAuth.getPassword().length();index++)
        {
            decoded += (char)(((userLoginAuth.getPassword().charAt((index)) - 32)+126)/2);
        }
        System.out.println(decoded);
        userLoginAuth.setPassword(decoded);
        Optional<MerchantLoginAuth> optionalMerchantLoginAuth = merchantAuthRepository.findByUserName(userLoginAuth.getUserName());
        if (optionalMerchantLoginAuth.isPresent()) {
            userLoginAuth.setIsMerchant(true);
            String password = optionalMerchantLoginAuth.get().getPassword();
            if(userLoginAuth.getPassword().equals(password)) {
                userLoginAuth.setId(optionalMerchantLoginAuth.get().getId());
                return userLoginAuth;
            }

        }
        return null;

    }

    @Override
    public MerchantLoginAuth merchantSave(MerchantLoginAuth merchantLoginAuth){
        String decoded ="";
        String regex = "^(.+)@(\\\\S+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(merchantLoginAuth.getEmail());
        if(!matcher.matches())
        {
            System.out.println("Invalid Email Address");
            return null;
        }

        for(int index=0;index<merchantLoginAuth.getPassword().length();index++)
        {
            decoded += (char)(((merchantLoginAuth.getPassword().charAt((index)) - 32)+126)/2);
        }
        System.out.println(decoded);
        Optional<MerchantLoginAuth> optionalMerchantLoginAuth = merchantAuthRepository.findByUserName(merchantLoginAuth.getUserName());

        merchantLoginAuth.setPassword(decoded);
        if(optionalMerchantLoginAuth.isPresent())
        {
            System.out.println("User already present");
            return null;
        }
      //  UserProfileModel userProfileModel =
        return merchantAuthRepository.save(merchantLoginAuth);
    }

    @Override
    public UserProfileModel save(UserProfileModel userProfileModel) {
        String decoded ="";

        for(int index=0;index<userProfileModel.getPassword().length();index++)
        {
            decoded += (char)(((userProfileModel.getPassword().charAt((index)) - 32)+126)/2);
        }
        System.out.println(decoded);
        Optional<UserProfileModel> optionalUserProfileModel = userProfileRepository.findByUserName(userProfileModel.getUserName());
        userProfileModel.setPassword(decoded);
        if(optionalUserProfileModel.isPresent())
        {
            System.out.println("User already present");
            return null;

        }
        return userProfileRepository.save(userProfileModel);

    }

    @Override
    public UserLoginAuth validationUserPass(UserLoginAuth userLoginAuth) {
        Optional<UserProfileModel> optionalUserProfileModel = userProfileRepository.findByUserName(userLoginAuth.getUserName());
        Optional<MerchantLoginAuth> optionalMerchantLoginAuth = merchantAuthRepository.findByUserName(userLoginAuth.getUserName());
        String decoded ="";
        for(int index=0;index<userLoginAuth.getPassword().length();index++)
        {
            decoded += (char)(((userLoginAuth.getPassword().charAt((index)) - 32)+126)/2);
        }
        System.out.println(decoded);
        userLoginAuth.setPassword(decoded);
        if(optionalUserProfileModel.isPresent()){
            userLoginAuth.setId(optionalUserProfileModel.get().getId());
            String password = optionalUserProfileModel.get().getPassword();
            if(userLoginAuth.getPassword().equals(password))

            {
                return userLoginAuth;
            }
            System.out.println("present");
        }
        else if(optionalMerchantLoginAuth.isPresent())
        {
            String merchantPassword = optionalMerchantLoginAuth.get().getPassword();
            if(userLoginAuth.getPassword().equals(merchantPassword))
            {
                return userLoginAuth;
            }
        }
        return null;
    }

    @Override
    public UserProfileModel update(UserProfileModel userProfileModel) {
        return userProfileRepository.save(userProfileModel);

    }



}