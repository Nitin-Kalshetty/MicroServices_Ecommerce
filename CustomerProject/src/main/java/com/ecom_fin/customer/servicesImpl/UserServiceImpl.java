package com.ecom_fin.customer.servicesImpl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecom_fin.customer.execptions.UserException;
import com.ecom_fin.customer.external.services.CartService;
import com.ecom_fin.customer.models.Cart;
import com.ecom_fin.customer.models.Users;
import com.ecom_fin.customer.repositories.UserRepository;
import com.ecom_fin.customer.sevices.UserService;



@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CartService cartService;

    
    
    @Autowired
    private RestTemplate restTemplate;
    
    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Users saveUser(Users user) {
        System.out.println("User Save Repo method");
        String randUID = UUID.randomUUID().toString();
        user.setUserId(randUID);
        // Cart cart = new Cart();
        // cart.setUserId(randUID);
        // Cart added_cart = cartService.addCart(cart);
        // user.setCart(added_cart);
        return userRepo.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Users getUser(String userId) {
        // return userRepo.findById(userId);
        Users user = userRepo.findById(userId).orElseThrow( () -> new UserException("User not found with Id : "+userId) );
        
        // using REST TEMPLATE

        // try{
        // Cart forObject = restTemplate.getForObject("http://CART_SERVICE/carts/"+userId, Cart.class);
        // logger.info("{}",forObject);
        // if(forObject!=null){
        //     user.setCart(forObject);
        // }
        // }catch(Exception e){
        //         System.out.println("Not added any products to cart...");
        // }
        
        
                

        // USING FEIGN CLIENT
        
        // try{
        // Cart cart =  cartService.getCartByUserId(userId);
        // user.setCart(cart);
        // }catch(Exception e){
        //     System.out.println("Not added any products to cart...");
        // }

        
       
        return user;
    }
    
}
