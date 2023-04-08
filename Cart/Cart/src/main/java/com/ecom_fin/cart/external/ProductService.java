package com.ecom_fin.cart.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom_fin.cart.models.Product;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductService {

    @GetMapping("/products/{productId}")
    Product getProductById(@PathVariable String productId);
    
}
