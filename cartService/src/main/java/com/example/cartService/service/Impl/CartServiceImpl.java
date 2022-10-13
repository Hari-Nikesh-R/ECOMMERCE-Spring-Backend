package com.example.cartService.service.Impl;

import com.example.cartService.entity.*;
import com.example.cartService.repository.CartServiceRepository;
import com.example.cartService.repository.OrderRepository;
import com.example.cartService.service.CartService;
import com.example.cartService.service.OrderService;
import com.example.cartService.urls.Urls;
import org.apache.catalina.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.cartService.service.Impl.OrderServiceImpl.mailInvoice;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartServiceRepository cartServiceRepository;

    @Autowired
    OrderService orderService;


    @Override
    public List<ProductCatalogModel> findCartById(int userId) {
        List<ProductCatalogModel> productCatalogModel = new ArrayList<>();
        List<Cart> cartItem = cartServiceRepository.findByUserId(userId);
        RestTemplate restTemplate = new RestTemplate();
        for(int index=0;index<cartItem.size();index++)
        {
            ProductCatalogModel product;
            try {
                 product = restTemplate.getForObject(Urls.product + "/catalog/" + cartItem.get(index).getProductId(), ProductCatalogModel.class);
            }
            catch(Exception e){
                product = restTemplate.getForObject(Urls.elastic+cartItem.get(index).getProductId(),ProductCatalogModel.class);
            }
            if(product==null){
                return null;
            }
            product.setPrice(cartItem.get(index).getPrice());
            product.setQuantity(cartItem.get(index).getQuantity());
            productCatalogModel.add(product);
        }
        return productCatalogModel;
    }

    @Override
    public Cart saveCart(CartDto cartDto) {
        RestTemplate restTemplate = new RestTemplate();
        UserProfileModel userProfileModel = restTemplate.getForObject(Urls.auth+"users/"+cartDto.getUserId(),UserProfileModel.class);
        ProductCatalogModel productCatalogModel;
        try {
            productCatalogModel = restTemplate.getForObject(Urls.product + "catalog/" + cartDto.getProductId(), ProductCatalogModel.class);
        }
        catch (Exception e)
        {
            productCatalogModel = restTemplate.getForObject(Urls.elastic+cartDto.getProductId(),ProductCatalogModel.class);
        }
        if(userProfileModel==null)
        {
            System.out.println("came to User");
            return null;
        }
        if(productCatalogModel==null)
        {
            System.out.println("Came to product");
            return null;
        }

        Optional<Cart> cart = cartServiceRepository.findByUserIdANDProductId(cartDto.getUserId(),cartDto.getProductId());

        if(cart.isPresent()) {
            System.out.println(cart.get().getQuantity());
            cart.get().setQuantity(cart.get().getQuantity()+1);
            return cartServiceRepository.save(cart.get());

        }
        else{
            Cart cartItem = new Cart();
            BeanUtils.copyProperties(cartDto,cartItem);
            return cartServiceRepository.save(cartItem);
        }


    }

    @Override
    public String deleteCartItem(CartDto cartDto) {
        Optional<Cart> optionalCartDto = cartServiceRepository.findByUserIdANDProductId(cartDto.getUserId(),cartDto.getProductId());
        if(optionalCartDto.isPresent())
        {
            cartServiceRepository.deleteById(optionalCartDto.get().getId());
            return "Cart Deleted Successfully";
        }
        return "Cart is not deleted";

    }


    // cart implementation
    @Override
    public ProductCatalogModel setQuantity(CartDto cartDto) {
        Cart cart = new Cart();
        RestTemplate restTemplate=new RestTemplate();
        BeanUtils.copyProperties(cartDto,cart);
        cart.setPrice(cartDto.getPrice()*cartDto.getQuantity());
        Optional<Cart> optionalCart = cartServiceRepository.findByUserIdANDProductId(cartDto.getUserId(),cartDto.getProductId());
        ProductCatalogModel product= restTemplate.getForObject(Urls.product+"catalog/"+cartDto.getProductId(),ProductCatalogModel.class);
        if(optionalCart.isPresent())
        {
            cart.setId(optionalCart.get().getId());
            cart.setPrice(product.getPrice());
            cart.setQuantity(cartDto.getQuantity());
            cart.setMerchantId(product.getMerchant_id());
            cartServiceRepository.save(cart);
        }

        System.out.println(cartDto.getProductId());
        product.setQuantity(cartDto.getQuantity());
        System.out.println(product.getPrice());
        return product;
    }

    @Override
    public ProductCatalogModel placedOrder(CartDto cartDto) {
        RestTemplate restTemplate = new RestTemplate();

        ProductCatalogModel productCatalogModel = restTemplate.getForObject(Urls.product+"catalog/"+cartDto.getProductId(),ProductCatalogModel.class);
        Order order = new Order();
        BeanUtils.copyProperties(cartDto,order);
            orderService.saveOrder(order);
            return productCatalogModel;


    }

    @Override
    public String placeAllOrder(int userId) {
        List<Cart> cartItem = cartServiceRepository.findByUserId(userId);
        Order order = new Order();
        List<Order> orderList = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        for(int index=0;index<cartItem.size();index++) {

            ProductCatalogModel product= restTemplate.getForObject(Urls.product+"catalog/"+cartItem.get(index).getProductId(),ProductCatalogModel.class);
            //order.setCartId(cartItem.get(index).getId());
            BeanUtils.copyProperties(cartItem.get(index),order);
            order.setCartId(cartItem.get(index).getId());
            try {
                order.setMerchantId(product.getMerchant_id());
                restTemplate.postForObject(Urls.order, order, Order.class);
                mailInvoice.add(order);
                orderList.add(order);
              //  restTemplate.postForObject(Urls.order,totalBillCheckOut,String.class);
            }
            catch(Exception e)
            {
                System.out.println(e);
                return "Order not placed";
            }


        }

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order,orderDto);
        orderDto.setOrderList(orderList);
       // restTemplate.postForObject(Urls.mail+"send",orderDto,String.class);
        return "Order has been placed";
//        RestTemplate restTemplate = new RestTemplate();
//        List<ProductCatalogModel> productCatalogModelList = new ArrayList<>();
//        ProductCatalogModel productCatalogModel;
//        int sum=0;
//
//        for(int cart=0; cart<cartDtos.size();cart++)
//        {
//            productCatalogModel = restTemplate.getForObject(Urls.product+"catalog"+cartDtos.get(cart).getProductId(),ProductCatalogModel.class);
//            sum+=productCatalogModel.getPrice();
//            productCatalogModel.setPrice(cartDtos.get(cart).getPrice()*cartDtos.get(cart).getQuantity());
//            productCatalogModelList.add(productCatalogModel);
//
//        }
//        return productCatalogModelList;

    }

    @Override
    public String deleteItem(CartDto cartDto) {
        Optional<Cart> optionalCartDto = cartServiceRepository.findByUserIdANDProductId(cartDto.getUserId(),cartDto.getProductId());
        if(optionalCartDto.isPresent()) {
            cartServiceRepository.deleteById(optionalCartDto.get().getId());
            return "Deleted successfully";
        }
        return "Not Deleted";
    }


}
