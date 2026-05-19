package com.project.staragile.banking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello from FinanceMe Bank";
    }

    @PostMapping("/createAccount")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PutMapping("/updateAccount/{accountNumber}")
    public Account updateAccount(
            @PathVariable int accountNumber,
            @RequestBody Account account) {
        return accountService.updateAccount(accountNumber, account);
    }

    @GetMapping("/viewPolicy/{accountNumber}")
    public Account viewPolicy(@PathVariable int accountNumber) {
        return accountService.getAccountDetails(accountNumber);
    }

    @DeleteMapping("/deletePolicy/{accountNumber}")
    public String deletePolicy(@PathVariable int accountNumber) {
        return accountService.deleteAccount(accountNumber);
    }
}