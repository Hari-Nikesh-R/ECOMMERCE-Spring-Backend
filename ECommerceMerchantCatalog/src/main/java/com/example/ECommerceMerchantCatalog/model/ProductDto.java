package com.example.ECommerceMerchantCatalog.model;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProductDto {

    private int id;
    private List<String> image;

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    private Date timeStamp;

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

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

    public String getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(String refreshRate) {
        this.refreshRate = refreshRate;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getVideoCaptureResolution() {
        return videoCaptureResolution;
    }

    public void setVideoCaptureResolution(String videoCaptureResolution) {
        this.videoCaptureResolution = videoCaptureResolution;
    }

    public String getImageCaptureResolution() {
        return imageCaptureResolution;
    }

    public void setImageCaptureResolution(String imageCaptureResolution) {
        this.imageCaptureResolution = imageCaptureResolution;
    }

    public String getCaptureSpeed() {
        return captureSpeed;
    }

    public void setCaptureSpeed(String captureSpeed) {
        this.captureSpeed = captureSpeed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }


    }



