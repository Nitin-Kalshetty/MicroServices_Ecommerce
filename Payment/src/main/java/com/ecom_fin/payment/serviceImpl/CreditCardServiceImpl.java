package com.ecom_fin.payment.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom_fin.payment.Repositories.paymentRepository;
import com.ecom_fin.payment.exceptions.PaymentException;
import com.ecom_fin.payment.models.CreditCard;
import com.ecom_fin.payment.services.CreditCardService;

@Service
public class CreditCardServiceImpl implements CreditCardService{

    @Autowired
    private paymentRepository paymentRepo;

    @Override
    public CreditCard addCreditCard(CreditCard card) {
        List<CreditCard> cards = paymentRepo.findAll();
        boolean checkDuplicate = cards.stream().anyMatch(element -> element.getCreditCardNumber().equals(card.getCreditCardNumber()));
        if(checkDuplicate){
            throw new PaymentException("Already card exists...");
        }
        return paymentRepo.save(card);
    }

    @Override
    public CreditCard getCreditCard(String creditCardNumber, Integer cvv, Integer password){
        List<CreditCard> cards = paymentRepo.findAll();
       return cards.stream().filter(card -> card.getCreditCardNumber().equals(cards)
                                                     && card.getCvv().equals(cvv)
                                                      && card.getCardPassword().equals(password))
                                                      .findFirst()
                                                      .orElseThrow(()-> new PaymentException("Wrong Credentials..."));
    }

    @Override
    public CreditCard withDrawAmount(String creditCardNumber, Integer cvv, Integer password, Integer expiryMonth,
            Integer expiryYear,Long balance) {

        CreditCard card = paymentRepo.findAll().stream().filter(ele -> ele.getCreditCardNumber().equals(creditCardNumber)
                                             &&  ele.getCvv().equals(cvv)
                                             &&  ele.getCardPassword().equals(password)
                                             &&  ele.getExpiryMonth().equals(expiryMonth)
                                             && ele.getExpirtyYear().equals(expiryYear))
                                             .findFirst()
                                             .orElseThrow(()-> new PaymentException("Wrong Credentials...") );

        if(card.getBalance()<balance){
            throw new PaymentException("Insufficient Balance please deposit first...");
        }
        card.setBalance(card.getBalance()-balance);
        return paymentRepo.save(card);
    }

    @Override
    public String addAmount(String creditCardNumber, Long amount) {
        CreditCard card = paymentRepo.findAll().stream().filter(ele -> ele.getCreditCardNumber().equals(creditCardNumber))
                                             .findFirst()
                                             .orElseThrow(()-> new PaymentException("No credit card found with this number") );

        if(card.getBalance()<amount){
            throw new PaymentException("Insufficient Balance please deposit first...");
        }
        card.setBalance(card.getBalance()+amount);
        paymentRepo.save(card);
        return "Amount added Successfully";
    }
    

}
