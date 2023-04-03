package com.ecom_fin.customer.servicesImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom_fin.customer.execptions.UserException;
import com.ecom_fin.customer.models.User;
import com.ecom_fin.customer.repositories.UserRepository;
import com.ecom_fin.customer.sevices.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

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
        return userRepo.findById(userId).orElseThrow( () -> new UserException("User not found with Id : "+userId) );
    }
    
}
