package com.example.ECommerceAuthService.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginAuth {

    private int id;
    private String userName;
    private String password;
    private Boolean isMerchant;

    public Boolean getMerchant() {
        return isMerchant;
    }

    public void setMerchant(Boolean merchant) {
        isMerchant = merchant;
    }

    public String getUserName() {

        return userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//
//    @Override
//    public String toString() {
//        return "UserLoginAuth{" +
//                "id=" + id +
//                ", userName='" + userName + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }
}
