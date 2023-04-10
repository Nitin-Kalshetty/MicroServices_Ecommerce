package com.ecom_fin.customer.external.securityConfig.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ecom_fin.customer.models.Users;
import com.ecom_fin.customer.repositories.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<Users> opt = userRepository.findByEmail(username);
		   if(opt.isEmpty()) {
		 	throw new BadCredentialsException("User Details not found with this email : "+username);  
		 }
		     Users user = opt.get();
		 List<GrantedAuthority> authorities = new ArrayList<>();
		 SimpleGrantedAuthority sga = new SimpleGrantedAuthority(user.getRole());
		 authorities.add(sga);
		 return new User(user.getEmail(),user.getPassword(),authorities);
    }


}
