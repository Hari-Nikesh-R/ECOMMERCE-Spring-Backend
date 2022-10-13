package com.example.cartService.service.Impl;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.cartService.entity.Cart;
import com.example.cartService.entity.Order;
import com.example.cartService.entity.OrderDto;
import com.example.cartService.entity.ProductCatalogModel;
import com.example.cartService.repository.CartServiceRepository;
import com.example.cartService.repository.OrderRepository;
import com.example.cartService.service.OrderService;
import com.example.cartService.urls.Urls;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartServiceRepository cartServiceRepository;

    public static List<Order> mailInvoice;

    public Order saveOrder(Order order) {
        RestTemplate restTemplate = new RestTemplate();
        ProductCatalogModel product = restTemplate.getForObject(Urls.product+"catalog/"+order.getProductId(),ProductCatalogModel.class);
        if(product.getStock()-order.getQuantity()>0)
        {
            restTemplate.getForObject(Urls.merchant+product.getMerchant_id()+"/update/"+order.getProductId()+"/"+(product.getStock() - order.getQuantity()),String.class);
        }
        System.out.println("came here Running mail.....");
        order.setMerchantId(product.getMerchant_id());
        Order orderedProduct = orderRepository.save(order);
        try {
            cartServiceRepository.deleteById(order.getCartId());
        }
        catch (Exception e) {
            System.out.println("NOT PRESENT IN CARD ORDER HAS PLACED");
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(order.getUserId());
        orderDto.setOrderList(mailInvoice);
        restTemplate.postForObject(Urls.mail+"send",orderDto,String.class);
        mailInvoice.clear();
        return orderedProduct;

    }

    @Override
    public List<ProductCatalogModel> getOrderHistory(int userId) {
        List<Order> optionalOrder = orderRepository.findByUserId(userId);
        List<ProductCatalogModel> productCatalogModelList = new ArrayList<>();
        try
        {
            RestTemplate restTemplate = new RestTemplate();
            for(int index=0;index<optionalOrder.size();index++) {
                ProductCatalogModel productCatalogModel = restTemplate.getForObject(Urls.product + "catalog/" + optionalOrder.get(index).getProductId(), ProductCatalogModel.class);
                BeanUtils.copyProperties(optionalOrder.get(index),  productCatalogModel);
                productCatalogModel.setPrice(optionalOrder.get(index).getPrice());
                productCatalogModelList.add(productCatalogModel);
            }
        }
        catch (Exception e)
        {
            System.out.println("Product Not Found");
            return productCatalogModelList;
        }
        return productCatalogModelList;

    }

    @Override
    public int getProductRatingCount(int productId) {
        return orderRepository.getRatingCount(productId);
    }


}
