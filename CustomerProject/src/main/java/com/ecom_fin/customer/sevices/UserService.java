package com.ecom_fin.customer.sevices;

import java.util.List;

import com.ecom_fin.customer.models.User;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    User getUser(String userId);
    
}
