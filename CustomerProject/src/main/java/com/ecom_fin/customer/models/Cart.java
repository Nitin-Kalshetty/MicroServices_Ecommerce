package com.ecom_fin.customer.models;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	
	private String cartId;
	private String userId;
	private List<Product> products = new ArrayList<>();

}
