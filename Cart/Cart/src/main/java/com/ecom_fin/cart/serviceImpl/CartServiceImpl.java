package com.ecom_fin.cart.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecom_fin.cart.exceptions.CartException;
import com.ecom_fin.cart.external.ProductService;
import com.ecom_fin.cart.models.Cart;
import com.ecom_fin.cart.models.Product;
import com.ecom_fin.cart.repositories.CartRepository;
import com.ecom_fin.cart.services.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductService productService;

	@Override
	public Cart saveCart(Cart cart) {
		Cart check = cartRepository.findByUserId(cart.getUserId());
		if(check!=null) {
			throw new CartException("there is already a user with this Id : "+cart.getUserId()+" and it is not a valid way to create.");
		}

		return cartRepository.save(cart);
	}

	@Override
	public List<Cart> getAllCarts() {
		return cartRepository.findAll();
	}

	@Override
	public Cart getCartByUserId(String userId) {
		Cart cart = cartRepository.findByUserId(userId);
		if(cart==null) {
			throw new CartException("there is no user with this Id : "+userId);
		}
		System.out.println(cart);
		return cart;
	}

	@Override
	public Cart addProductToCart(String userId,String productId) {
		Cart cart = cartRepository.findByUserId(userId);
		if(cart==null) {
			throw new CartException("Not a valid userId...");
		}
		System.out.print(cart);
		boolean checkingIsProductAlreadyPresent = cart.getProducts().stream()
				.anyMatch(pro -> pro.getProductId().equals(productId));
		System.out.println(checkingIsProductAlreadyPresent);
		if(checkingIsProductAlreadyPresent) {
			throw new CartException("Already this product is in cart So please if want another product you can add quantity.");
		}
		// System.out.println("I am working above template");
		//  ResponseEntity<Product> productResp = restTemplate.getForEntity("http://PRODUCT-SERVICE/products/"+productId, Product.class);
		//  Product product = productResp.getBody();
		// Product product1 = restTemplate.getForObject("http://PRODUCT-SERVICE/products/"+productId, Product.class);	
		// System.out.println(product1);
		Product product = productService.getProductById(productId);
		cart.getProducts().add(product);
		return cartRepository.save(cart);
		
	}

	@Override
	public Cart removeProductFromCart(String userId,String productId) {
		Cart cart = cartRepository.findByUserId(userId);
		if(cart==null) {
			throw new CartException("Not a valid userId...");
		}
		boolean removed = cart.getProducts().removeIf((product) -> product.getProductId().equals(productId));
		if(!removed) {
			throw new CartException("No product available to remove. Its invalid way to remove.");
		}
		return cartRepository.save(cart);
	}
	

}
