package com.ecom_fin.customer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom_fin.customer.models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,String>{
    Optional<Users> findByEmail(String email);
}
