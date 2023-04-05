package com.ecom_fin.customer.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private String productId;
    
    private String name;
    
    private String category;

    private Integer price;

    private Integer stock;

    private String description;

    private LocalDateTime product_added;
}
