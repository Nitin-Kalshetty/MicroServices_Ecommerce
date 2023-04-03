package com.ecom_fin.products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom_fin.products.models.Product;
import com.ecom_fin.products.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProductControllerHandler(@RequestBody Product product){
        return new ResponseEntity<Product>(productService.saveProduct(product),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProductsControllerHandler(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.ACCEPTED);
    }


    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductByIdControllerHandler(@PathVariable String productId){
        return new ResponseEntity<>(productService.getProductById(productId),HttpStatus.ACCEPTED);
    }

}
