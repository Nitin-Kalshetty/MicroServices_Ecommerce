package com.rockerBank.details.models;

import java.time.LocalDate;

import com.rockerBank.details.externals.models.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountHolders {
    
    @Id
    private String accountNumber;

    private String ifsc_code;

    private LocalDate accountCreated;

    private Long balance;

    @Column(unique = true)
    private String userId;

    private LocalDate dateOfBirth;

    private String pancard;

    private String bankingUsername;

    private String bankingPassword;

}
