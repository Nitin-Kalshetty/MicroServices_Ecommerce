package com.rockerBank.details.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rockerBank.details.models.AccountHolders;
import com.rockerBank.details.services.AccountHolderService;

@RestController
@RequestMapping("/bank")
public class BankingController {
    
    @Autowired
    private AccountHolderService accountService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<AccountHolders> addAccountHandler(@RequestBody AccountHolders account,@PathVariable String userId){
        return new ResponseEntity<>(accountService.creaAccountHolder(account, userId),HttpStatus.CREATED);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<AccountHolders> getAccountByUserIdHandler(@PathVariable String userId){
        return new ResponseEntity<AccountHolders>(accountService.getAccountHolderByUserId(userId), HttpStatus.OK);
    }

    @PutMapping("/addMoney")
    public ResponseEntity<AccountHolders> addAmountToAccount(@RequestParam String creditCardNumber,
                                                            @RequestParam String cvv,
                                                            @RequestParam Integer expiryMonth,
                                                            @RequestParam Integer expiryYear,
                                                            @RequestParam Integer balance,
                                                            @RequestParam String username,
                                                            @RequestParam String password ){

        return new ResponseEntity<>(accountService.addAmountToAccount(creditCardNumber, cvv, expiryMonth, expiryYear, balance, username, password),HttpStatus.OK);

    }


    @PutMapping("transfer")
    public ResponseEntity<AccountHolders> transferAmountFromOneToOtherHandler(@RequestParam String username,
                                                                              @RequestParam String password,
                                                                              @RequestParam Integer amount,
                                                                              @RequestParam String receiverId){

        return new ResponseEntity<>(accountService.transferBalanceFromOneAccountToAnother(username, password, amount, receiverId),HttpStatus.ACCEPTED);
    }

    @GetMapping("confirm")
    public ResponseEntity<String> confirmNameHandler(@RequestParam String username,
                                                     @RequestParam String password,
                                                     @RequestParam String receiverId){
        return new ResponseEntity<>(accountService.confirmNameBeforeSendingAmount(username, password, receiverId),HttpStatus.ACCEPTED);
    }
}
