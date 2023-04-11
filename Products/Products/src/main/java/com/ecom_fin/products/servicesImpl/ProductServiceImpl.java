package com.ecom_fin.products.servicesImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom_fin.products.exceptions.ProductException;
import com.ecom_fin.products.models.Product;
import com.ecom_fin.products.repositories.ProductRepository;
import com.ecom_fin.products.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
    
    @Autowired
    private ProductRepository productRepo;

    @Override
    public Product saveProduct(Product product) {
        String UID = UUID.randomUUID().toString();
        product.setProductId(UID);
        product.setProduct_added(LocalDateTime.now());
        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(String productId) {
        return productRepo.findById(productId).orElseThrow(()-> new ProductException("Product not found with Id : "+productId) );
    }
}
