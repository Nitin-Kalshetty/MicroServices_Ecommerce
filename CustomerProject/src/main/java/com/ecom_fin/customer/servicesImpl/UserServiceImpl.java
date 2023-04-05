package com.ecom_fin.customer.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecom_fin.customer.execptions.UserException;
import com.ecom_fin.customer.models.Cart;
import com.ecom_fin.customer.models.User;
import com.ecom_fin.customer.repositories.UserRepository;
import com.ecom_fin.customer.sevices.UserService;



@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;
    
    
    @Autowired
    private RestTemplate restTemplate;
    
    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randUID = UUID.randomUUID().toString();
        user.setUserId(randUID);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {
        // return userRepo.findById(userId);
        User user = userRepo.findById(userId).orElseThrow( () -> new UserException("User not found with Id : "+userId) );
        ArrayList<Cart> forObject = restTemplate.getForObject("http://localhost:8883/carts/"+userId, ArrayList.class);
        logger.info("{}",forObject);
        user.setCart(forObject);
        return user;
    }
    
}
