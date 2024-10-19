package com.tapiwa.learn.java.ee.services;

import com.tapiwa.learn.java.ee.models.BankAccount;

import java.util.List;

public interface BankAccountService {
    List<BankAccount> getAllBankAccounts();
    BankAccount getOnebankAccount(Long id);
    BankAccount createBankAccount(BankAccount bankAccount);
    BankAccount updateBankAccount(Long id, BankAccount bankAccount);
    void deleteBankAccount(Long id);

}
