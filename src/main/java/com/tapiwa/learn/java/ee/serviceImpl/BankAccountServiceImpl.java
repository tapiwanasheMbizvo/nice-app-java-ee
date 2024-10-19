package com.tapiwa.learn.java.ee.serviceImpl;

import com.tapiwa.learn.java.ee.models.BankAccount;
import com.tapiwa.learn.java.ee.services.BankAccountService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named
@ApplicationScoped
public class BankAccountServiceImpl implements BankAccountService {

    private static final String BANK_PU = "bank-accounts-pu";
    @PersistenceContext(unitName = BANK_PU)
    private EntityManager entityManager;



    @Override
    public List<BankAccount> getAllBankAccounts() {
        var query =
                entityManager.createQuery("SELECT account from BankAccount", BankAccount.class);
        return query.getResultList();
    }

    @Override
    public BankAccount getOnebankAccount(Long id) {
        return entityManager.find(BankAccount.class, id);
    }

    @Override
    public BankAccount createBankAccount(BankAccount bankAccount) {
       entityManager.getTransaction().begin();
       entityManager.persist(bankAccount);
       entityManager.getTransaction().commit();
       return bankAccount;
    }

    @Override
    public BankAccount updateBankAccount(Long id, BankAccount updatedEntity) {
        entityManager.getTransaction().begin();
        var account = entityManager.find(BankAccount.class, id);
        if (account!=null){

            account.setAccountNumber(updatedEntity.getAccountNumber());
            account.setBalance(updatedEntity.getBalance());
            entityManager.getTransaction().commit();
        }else{
            entityManager.getTransaction().rollback();
        }
        return account;
    }

    @Override
    public void deleteBankAccount(Long id) {

        entityManager.getTransaction().begin();
        var account = entityManager.find(BankAccount.class, id);

        if(account !=null){
            entityManager.remove(account);
        }
        entityManager.getTransaction().commit();

    }
}
