package com.example.ECommerceUserCatalog.controller;


import com.example.ECommerceUserCatalog.model.ProductCatalogModel;
import com.example.ECommerceUserCatalog.model.RatingDto;
import com.example.ECommerceUserCatalog.repository.UserCatalogRepository;
import com.example.ECommerceUserCatalog.service.UserCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductCatalogController {
    @Autowired
    UserCatalogService userCatalogService;

    @Autowired
    UserCatalogRepository userCatalogRepository;

    @GetMapping(value = "/catalog")
    public List<ProductCatalogModel> getCatalogue()
    {
        return userCatalogService.getAllProduct();
    }

    @GetMapping(value = "/catalog/{id}")
    public ProductCatalogModel getProduct(@PathVariable("id") int id){
        return userCatalogService.getProduct(id);
    }

    @GetMapping(value = "/catalog/{title}/review")
    public double getReview(@PathVariable("title") String title)
    {
        return userCatalogService.getReview(title);
    }

    @GetMapping(value = "/catalog/newarrivals")
    public List<ProductCatalogModel> getNewArrivals(){
        return userCatalogService.getNewArrivals();
    }


    @PostMapping
    public ProductCatalogModel postProduct(@RequestBody ProductCatalogModel productCatalogModel)
    {
        System.out.println(productCatalogModel.getMerchant_id());
        return userCatalogService.save(productCatalogModel);
    }
    @DeleteMapping(value = "/catalog/product/{id}")
    public void deleteProduct(@PathVariable("id") int id)
    {
        userCatalogService.deleteByProduct(id);
    }

    // Need to implemen
    @GetMapping(value = "/catalog/brand/{brand}")
    public List<ProductCatalogModel> displayBrand(@PathVariable("brand") String brand)
    {

        return userCatalogService.getByBrand(brand);
    }

    // Need to implement
    @GetMapping(value = "/catalog/category/{category}")
    public List<ProductCatalogModel> displayCategory(@PathVariable("category") String category)
    {
        return userCatalogService.getByCategory(category);
    }

    @PostMapping(value = "/rating")
    public ProductCatalogModel setRating(@RequestBody RatingDto ratingDto)
    {
        return userCatalogService.setRating(ratingDto);
    }

    @PostMapping(value = "/update/stock")
    public ProductCatalogModel updateProductStock(@RequestBody ProductCatalogModel productCatalogModel)
    {
        return userCatalogService.stockSave(productCatalogModel);
    }

    @PostMapping(value = "/update/product")
    public ProductCatalogModel updateProduct(@RequestBody ProductCatalogModel productCatalogModel)
    {
        return userCatalogRepository.save(productCatalogModel);
    }






}
