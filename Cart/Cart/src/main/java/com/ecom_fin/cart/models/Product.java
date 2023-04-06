package com.ecom_fin.cart.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
	private String productId;

    private String name;

    
    private String category;

    private Integer price;

    private Integer stock;

    private String description;

    private LocalDateTime product_added;
}
