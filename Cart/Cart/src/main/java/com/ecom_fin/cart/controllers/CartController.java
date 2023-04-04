package com.ecom_fin.cart.controllers;

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

import com.ecom_fin.cart.models.Cart;
import com.ecom_fin.cart.services.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping
	public ResponseEntity<Cart> saveCartControllerHandler(@RequestBody Cart cart){
		return new ResponseEntity<>(cartService.saveCart(cart),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Cart>> getAllCartsControllerHandler(){
		return new ResponseEntity<List<Cart>>(cartService.getAllCarts(),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<Cart>> getCartByUserId(@PathVariable String userId){
		return new ResponseEntity<List<Cart>>(cartService.getCartByUserId(userId),HttpStatus.ACCEPTED);
	}
}