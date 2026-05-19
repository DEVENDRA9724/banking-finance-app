package com.project.staragile.banking;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(int accountNumber, Account account) {
        Optional<Account> existing = accountRepository.findById(accountNumber);
        if (existing.isPresent()) {
            Account acc = existing.get();
            acc.setAccountName(account.getAccountName());
            acc.setAccountType(account.getAccountType());
            acc.setAccountBalance(account.getAccountBalance());
            return accountRepository.save(acc);
        }
        return null;
    }

    public Account getAccountDetails(int accountNumber) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        return account.orElse(null);
    }

    public String deleteAccount(int accountNumber) {
        Optional<Account> existing = accountRepository.findById(accountNumber);
        if (existing.isPresent()) {
            accountRepository.deleteById(accountNumber);
            return "Account " + accountNumber + " deleted successfully";
        }
        return "Account not found";
    }
}