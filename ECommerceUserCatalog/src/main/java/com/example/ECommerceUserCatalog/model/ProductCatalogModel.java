package com.example.ECommerceUserCatalog.model;



import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "products")
public class ProductCatalogModel {

    @Id
    private int id;

    private List<String> image;

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