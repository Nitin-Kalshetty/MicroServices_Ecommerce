package com.rockerBank.details.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rockerBank.details.models.AccountHolders;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolders,String>{
    Optional<AccountHolders> findByUserId(String userId);


    Optional<AccountHolders> findByBankingUsername(String bankingUsername);
    
}
