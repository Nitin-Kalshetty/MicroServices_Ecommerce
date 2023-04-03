package com.ecom_fin.products.exceptions;

public class ProductException extends RuntimeException {
    
    public ProductException(){

    }
    public ProductException(String message){
        super(message);
    }
}
