package com.rockerBank.details.exceptions;

public class InsufficientBalance extends RuntimeException{
    public InsufficientBalance(){
        super("Insufficient Balance Please Deposit First...");
    }

    public InsufficientBalance(String msg){
        super(msg);
    }
}
