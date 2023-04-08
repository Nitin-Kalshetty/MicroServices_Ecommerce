package com.ecom_fin.cart.models;


import java.util.ArrayList;
import java.util.List;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

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
@Document
public class Cart {

	@MongoId
	private String cartId;
	private String userId;
	private List<Product> products = new ArrayList<>();
}
