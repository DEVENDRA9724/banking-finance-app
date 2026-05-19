package com.project.staragile.banking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(AccountRepository repository) {
        return args -> {
            // Preload sample accounts
            if (repository.count() == 0) {
                repository.save(new Account(1001, "Devendra Sharma", "Savings", 50000));
                repository.save(new Account(1002, "Rahul Verma", "Current", 75000));
                repository.save(new Account(1003, "Priya Patel", "Savings", 30000));
                repository.save(new Account(1004, "Amit Kumar", "Business", 100000));
                System.out.println("Preloaded 4 sample accounts!");
            }
        };
    }
}