package com.rockerBank.details.services;

import com.rockerBank.details.models.AccountHolders;

public interface AccountHolderService {
    
    public AccountHolders creaAccountHolder(AccountHolders account,String userId);

    public AccountHolders getAccountHolderByUserId(String UserId);

    public AccountHolders getAccountHolderByOnlineBankingCredentials(String username,String password);

    public AccountHolders changePasswordOfOnlineBankingCredentials(String username,String oldPassword,String newPassword);

    public AccountHolders deleteAccountHolderByOnlineBankingCredentials(String username,String password);

    public AccountHolders addAmountToAccount(String creditCardNumber,String cvv,Integer expiryMonth,Integer expiryYear,Integer balance,String username,String password);

    public AccountHolders transferBalanceFromOneAccountToAnother(String username,String password,Integer sendingAmount,
                                                                String receiverId);


}
