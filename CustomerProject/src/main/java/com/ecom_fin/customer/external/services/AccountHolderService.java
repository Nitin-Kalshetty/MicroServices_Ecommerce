package com.ecom_fin.customer.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom_fin.customer.models.AccountHolders;

@FeignClient(name = "ROCKER-BANK")
public interface AccountHolderService {
    
    @PostMapping("/bank/add/{userId}")
    AccountHolders creaAccountHolder(@RequestBody AccountHolders account,@PathVariable String userId);

    @GetMapping("/bank/{userId}")
    AccountHolders getAccountHolderByUserId(@PathVariable String UserId);
}
