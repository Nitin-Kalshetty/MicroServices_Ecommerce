package com.ecom_fin.customer.execptions;

public class UserException extends RuntimeException {
    
    public UserException(){
        
    }


    public UserException(String message){
        super(message);
    }

}
