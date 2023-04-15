package com.ecom_fin.payment.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecom_fin.payment.models.CreditCard;

@Repository
public interface paymentRepository extends MongoRepository<CreditCard,String>{
    
}
