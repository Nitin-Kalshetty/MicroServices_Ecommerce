package com.ecom_fin.customer.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    
    @Id
    private String userId;

    private String name;

    private String email;

    @Column(length = 10)
    private String mobile;
    
    private String password;
    
    @Transient
    private List<Cart> cart;
}
