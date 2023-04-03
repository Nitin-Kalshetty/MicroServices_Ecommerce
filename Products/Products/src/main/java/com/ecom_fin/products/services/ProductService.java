package com.ecom_fin.products.services;

import java.util.List;

import com.ecom_fin.products.models.Product;

public interface ProductService {
    

    Product saveProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(String productId);
}
