package com.ecom_fin.cart.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecom_fin.cart.models.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, String>{

	Cart findByUserId(String userId);
	
}
