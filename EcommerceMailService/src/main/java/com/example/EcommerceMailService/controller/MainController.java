package com.example.EcommerceMailService.controller;

import com.example.EcommerceMailService.model.*;
import com.example.EcommerceMailService.service.MailService;
import com.example.EcommerceMailService.urls.Urls;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/mail")
public class MainController {

    @Autowired
    MailService mailService;

    @PostMapping("/send")
    public String sendMail(@RequestBody OrderModel order)
    {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(order.getUserId());

        UserProfileModel userProfileModel = restTemplate.getForObject(Urls.auth+"users/"+order.getUserId(),UserProfileModel.class);

        String subject = "Your Order is sucessfully Placed";
        System.out.println(userProfileModel.getEmail());
        String body = "";
        for(int index=0;index<order.getOrderList().size();index++) {

            ProductCheckOut productCatalogModel = restTemplate.getForObject(Urls.product + "catalog/" +order.getOrderList().get(index).getProductId(),ProductCheckOut.class);
            ProductCatalogModel product = new ProductCatalogModel();
            BeanUtils.copyProperties(productCatalogModel,product);
            body += product.toString();


        }
        return mailService.sendMail(userProfileModel.getEmail(),subject,body);
        //return mailService.sendMail("hari.nikesh.r.cce@gmail.com",subject,body);
    }
}
