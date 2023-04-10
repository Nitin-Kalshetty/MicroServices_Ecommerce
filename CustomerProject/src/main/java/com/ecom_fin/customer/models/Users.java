package com.ecom_fin.customer.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
public class Users {
    
    @Id
    private String userId;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true,length = 10)
    private String mobile;

    private String role;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    
    @Transient
    private Cart cart;
}
