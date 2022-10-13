package com.example.cartService.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class ProductCatalogModel {
    private int id;
    private List<String> image;
    private int quantity;

    private Date timeStamp;

    private String title;

    private int merchant_id;

    private String description;

    private String category;

    private int price;

    private int stock;

    private double rating;

    private String brand;

    private String color;

    private String processor;

    private String size;

    private String ram;

    private String refreshRate;

    private String screenSize;

    private String connectionType;

    private String videoCaptureResolution;

    private String imageCaptureResolution;

    private String captureSpeed;

}
