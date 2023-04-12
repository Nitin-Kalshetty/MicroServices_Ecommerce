package com.ecom_fin.customer.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom_fin.customer.models.Cart;

@FeignClient(name = "CART-SERVICE")
public interface CartService {

    @GetMapping("/carts/{userId}")
    Cart getCartByUserId(@PathVariable String userId); 
    
    
    @PostMapping("/carts")
    Cart addCart(@RequestBody Cart cart);

    @GetMapping("/carts")
    List<Cart> getAllCarts();
}
