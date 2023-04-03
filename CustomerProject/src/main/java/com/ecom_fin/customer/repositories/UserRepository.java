package com.ecom_fin.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom_fin.customer.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
    
}
