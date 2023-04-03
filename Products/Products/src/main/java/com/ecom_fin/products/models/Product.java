package com.ecom_fin.products.models;

import java.time.LocalDateTime;

import com.ecom_fin.products.enums.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    private String productId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer price;

    private Integer stock;

    private String description;

    private LocalDateTime product_added;
    
}
