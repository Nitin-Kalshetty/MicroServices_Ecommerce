package com.rockerBank.details.servicesImpl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rockerBank.details.exceptions.AccountHolderException;
import com.rockerBank.details.exceptions.InsufficientBalance;
import com.rockerBank.details.models.AccountHolders;
import com.rockerBank.details.repositories.AccountHolderRepository;
import com.rockerBank.details.services.AccountHolderService;

import jakarta.transaction.Transactional;

public class AccountHolderServiceImpl implements AccountHolderService{

    @Autowired
    private AccountHolderRepository accountRepo;

    @Override
    public AccountHolders creaAccountHolder(AccountHolders account,String userId) {
        account.setIfsc_code("ROCKER3851");
        account.setAccountCreated(LocalDate.now());
        account.setUserId(userId);
        account.setBalance(0L);
        String pan = account.getPancard();
        if(pan.equals("") || pan.equals(" ")) account.setPancard("NA");
        return accountRepo.save(account);
    }

    @Override
    public AccountHolders getAccountHolderByUserId(String UserId) {
        return accountRepo.findByUserId(UserId).orElseThrow(() -> new AccountHolderException("Invalid userId..."));
    }

    @Override
    public AccountHolders getAccountHolderByOnlineBankingCredentials(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountHolderByOnlineBankingCredentials'");
    }

    @Override
    public AccountHolders changePasswordOfOnlineBankingCredentials(String username, String oldPassword,
            String newPassword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changePasswordOfOnlineBankingCredentials'");
    }

    @Override
    public AccountHolders deleteAccountHolderByOnlineBankingCredentials(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAccountHolderByOnlineBankingCredentials'");
    }

    @Override
    public AccountHolders addAmountToAccount(String creditCardNumber, String cvv, Integer expiryMonth,
            Integer expiryYear, Integer balance, String username, String password) {

        
        Optional<AccountHolders> opt = accountRepo.findById(username);
        AccountHolders account = null;
        if(opt.isPresent()){
            account = opt.get();
        }else{
            account = accountRepo.findByBankingUsername(username).orElseThrow( () -> new AccountHolderException("No user is registered with this Id : "+username) );
        }
        account.setBalance(balance.longValue());
        return accountRepo.save(account);
        
    }

    @Override
    @Transactional
    public AccountHolders transferBalanceFromOneAccountToAnother(String username, String password, Integer sendingAmount,
            String receiverId) {
                Optional<AccountHolders> opt = accountRepo.findById(username);
                AccountHolders sender = null;
                if(opt.isPresent()){
                    sender = opt.get();
                }else{
                    sender = accountRepo.findByBankingUsername(username).orElseThrow( () -> new AccountHolderException("No user is registered with this Id : "+username) );
                }

                Optional<AccountHolders> opt1 = accountRepo.findById(username);
                AccountHolders receiver = null;
                if(opt1.isPresent()){
                    receiver = opt.get();
                }else{
                    receiver = accountRepo.findByBankingUsername(username).orElseThrow( () -> new AccountHolderException("No user is registered with this Id : "+username) );
                }
                if(sender.getBalance()<sendingAmount){
                    throw new InsufficientBalance();
                }
                sender.setBalance(sender.getBalance()-sendingAmount);
                receiver.setBalance(receiver.getBalance()+sendingAmount);
                accountRepo.save(sender);
                accountRepo.save(receiver);
                return sender;
        
    }

  

    


    
}
