package com.ecom_fin.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom_fin.products.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,String>{
    
}
