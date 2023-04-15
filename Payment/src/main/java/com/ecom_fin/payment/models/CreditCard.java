package com.ecom_fin.payment.models;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class CreditCard {
    
    @MongoId
    private String creditCardNumber;

    private String cardHolderName;

    private Integer cvv;

    private Integer expiryMonth;

    private Integer expirtyYear;

    private Integer cardPassword;

    private String mobileNumber;

    private String cardName;

    private boolean international = false;

    private long balance;
}
