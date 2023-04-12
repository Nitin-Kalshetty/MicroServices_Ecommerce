package com.ecom_fin.customer.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolders {


    private String accountNumber;

    private String ifsc_code;

    private LocalDate accountCreated;

    private Long balance;

    private String userId;

    private LocalDate dateOfBirth;

    private String pancard;

    private String bankingUsername;

    private String bankingPassword;
}
