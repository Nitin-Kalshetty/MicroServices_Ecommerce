package com.ecom_fin.payment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom_fin.payment.models.CreditCard;
import com.ecom_fin.payment.services.CreditCardService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    
    @Autowired 
    private CreditCardService cardService;


    @PostMapping("/register")
    public ResponseEntity<CreditCard> registerCardHandler(@RequestBody CreditCard card){
        return new ResponseEntity<>(cardService.addCreditCard(card),HttpStatus.CREATED);
    }

   
}
