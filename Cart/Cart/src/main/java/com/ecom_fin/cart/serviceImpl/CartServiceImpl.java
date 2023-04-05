package com.ecom_fin.cart.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecom_fin.cart.exceptions.CartException;
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

	@Override
	public Cart saveCart(Cart cart) {
		return cartRepository.save(cart);
	}

	@Override
	public List<Cart> getAllCarts() {
		return cartRepository.findAll();
	}

	@Override
	public Cart getCartByUserId(String userId) {
		List<Cart> carts = cartRepository.findByUserId(userId);
		if(carts.isEmpty()) {
			throw new CartException("there is userWith this Id : "+userId);
		}
		return carts.get(0);
	}

	@Override
	public Cart addProductToCart(String userId,String productId) {
		System.out.println(userId+" : userid: " +productId+" :productId: ");
		List<Cart> cart1 = cartRepository.findByUserId(userId);
		System.out.println(cart1);
		if(cart1.isEmpty()) {
			throw new CartException("Not a valid userId...");
		}
		Cart cart = cart1.get(0);
		System.out.println(cart);
		boolean checkingIsProductAlreadyPresent = cart.getProducts().stream()
				.anyMatch(pro -> pro.getProductId().equals(productId));
		System.out.println(checkingIsProductAlreadyPresent);
		if(checkingIsProductAlreadyPresent) {
			throw new CartException("Already this product is in cart So please if want another product you can add quantity.");
		}
		Product product = restTemplate.getForObject("http://PRODUCT_SERVICE/product/"+productId, Product.class);	
		System.out.println(product);
		cart.getProducts().add(product);
		return cartRepository.save(cart);
		
	}

	@Override
	public Cart removeProductFromCart(String userId,String productId) {
		List<Cart> cart1 = cartRepository.findByUserId(userId);
		if(cart1.isEmpty()) {
			throw new CartException("Not a valid userId...");
		}
		Cart cart = cart1.get(0);
		boolean removed = cart.getProducts().removeIf((product) -> product.getProductId()==productId);
		if(removed) {
			throw new CartException("No product available to remove. Its invalid way to remove.");
		}
		return cartRepository.save(cart);
	}
	

}
