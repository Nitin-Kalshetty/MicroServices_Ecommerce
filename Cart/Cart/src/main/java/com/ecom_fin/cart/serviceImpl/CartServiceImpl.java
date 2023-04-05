package com.ecom_fin.cart.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom_fin.cart.models.Cart;
import com.ecom_fin.cart.repositories.CartRepository;
import com.ecom_fin.cart.services.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public Cart saveCart(Cart cart) {
		return cartRepository.save(cart);
	}

	@Override
	public List<Cart> getAllCarts() {
		return cartRepository.findAll();
	}

	@Override
	public List<Cart> getCartByUserId(String userId) {
		return cartRepository.findByUserId(userId);
	}
	

}
