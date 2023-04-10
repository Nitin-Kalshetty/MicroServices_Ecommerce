package com.ecom_fin.customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom_fin.customer.models.Users;
import com.ecom_fin.customer.repositories.UserRepository;

@RestController
public class LoginController {
    
    @Autowired
    public UserRepository userRepository;

    @GetMapping("/signIn")
	public ResponseEntity<Users> getLoggedInCustomerDetailsHandler(Authentication auth){
		System.out.println("auth"+auth);
		Users user = userRepository.findByEmail(auth.getName()).orElseThrow(() -> new BadCredentialsException("Wrong Credentials"));
		return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
	}
}
