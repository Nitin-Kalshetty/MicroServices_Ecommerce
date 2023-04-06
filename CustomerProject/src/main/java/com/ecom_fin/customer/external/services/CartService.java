package com.ecom_fin.customer.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom_fin.customer.models.Cart;

@FeignClient(name = "CART-SERVICE")
public interface CartService {

    @GetMapping("/carts/{userId}")
    Cart getCartByUserId(@PathVariable String userId); 
}
