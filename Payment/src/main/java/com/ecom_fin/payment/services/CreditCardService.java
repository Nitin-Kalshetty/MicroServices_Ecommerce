package com.ecom_fin.payment.services;

import com.ecom_fin.payment.models.CreditCard;

public interface CreditCardService {
    
    CreditCard addCreditCard(CreditCard card);

    CreditCard getCreditCard(String creditCardNumber, Integer cvv, Integer password);

    CreditCard withDrawAmount(String creditCardNumber, Integer cvv, Integer password, Integer expiryMonth, Integer expiryYear,Long balance);

    String addAmount(String creditCardNumber, Long amount);
}
