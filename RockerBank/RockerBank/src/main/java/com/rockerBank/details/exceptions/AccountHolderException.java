package com.rockerBank.details.exceptions;

public class AccountHolderException extends RuntimeException{
    public AccountHolderException(){

    }

    public AccountHolderException(String msg){
        super(msg);
    }

}
