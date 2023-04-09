package com.ecom_fin.customer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom_fin.customer.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
    Optional<User> findByEmail(String email);
}
