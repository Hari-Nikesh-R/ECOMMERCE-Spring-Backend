package com.example.ECommerceAuthService.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name="merchantlogin")
public class MerchantLoginAuth {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String userName;
    private String password;
    private String name;
    private String email;
    private String number;
    private String address;
    private String companyName;
    private String aboutUs;

}
