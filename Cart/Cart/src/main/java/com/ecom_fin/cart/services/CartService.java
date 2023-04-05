package com.ecom_fin.cart.services;

import java.util.List;

import com.ecom_fin.cart.models.Cart;

public interface CartService {

	Cart saveCart(Cart cart);
	
	List<Cart> getAllCarts();
	
	Cart getCartByUserId(String userId);
	
	Cart addProductToCart(String userId,String productId);
	
	Cart removeProductFromCart(String userId,String productId);
	
}
