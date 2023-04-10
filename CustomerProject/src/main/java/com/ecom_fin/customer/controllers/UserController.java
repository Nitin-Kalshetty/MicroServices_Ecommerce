package com.ecom_fin.customer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom_fin.customer.models.Users;
import com.ecom_fin.customer.repositories.UserRepository;
import com.ecom_fin.customer.sevices.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @CircuitBreaker(name="CB-CART")
    public ResponseEntity<Users> saveUserControllerHandler(@RequestBody Users user){
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name="CB-CART-PRODUCT",fallbackMethod = "cartProductFallBack")
    public ResponseEntity<Users> getUserByIdControllerHandler(@PathVariable String userId){
        return new ResponseEntity<>(userService.getUser(userId),HttpStatus.ACCEPTED);
    }
    
    // cartProductFallBack method for Circuit Breaker
    
    public ResponseEntity<Users> cartProductFallBack(String userId,Exception ex){
    	Users user = Users.builder().email("dummy@email.com").name("Dummy").mobile("7878787878")
    	.password("password").build();
    	return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsersControllerHandler(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.ACCEPTED);
    }



    @GetMapping("/signIn")
    public ResponseEntity<Users> getLoggedInCustomerDetailsHandler(Authentication auth){
        Users user = userRepository.findByEmail(auth.getName()).orElseThrow(() -> new BadCredentialsException("Wrong Credentials"));
		return new ResponseEntity<Users>(user,HttpStatus.ACCEPTED);

    }




}
