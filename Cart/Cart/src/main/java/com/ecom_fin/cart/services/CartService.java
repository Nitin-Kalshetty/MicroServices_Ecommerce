package com.ecom_fin.cart.services;

import java.util.List;

import com.ecom_fin.cart.models.Cart;
import com.ecom_fin.cart.models.Product;

public interface CartService {

	Cart saveCart(Cart cart);
	
	List<Cart> getAllCarts();
	
	List<Cart> getCartByUserId(String userId);
	
	List<Cart> addProductToCart(String productId);
	
	List<Cart> removeProductFromCart(String productId);
	
}
