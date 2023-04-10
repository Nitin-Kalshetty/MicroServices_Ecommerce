package com.ecom_fin.customer.sevices;

import java.util.List;

import com.ecom_fin.customer.models.Users;

public interface UserService {

    Users saveUser(Users user);

    List<Users> getAllUsers();

    Users getUser(String userId);
    
}
