package com.example;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
public class AccountService {
    private final CopyOnWriteArraySet<Account> accounts = new CopyOnWriteArraySet();

    public boolean register(Account account) {
        return this.accounts.add(account);
    }

    public Optional<Account> get(Account account) {
        return this.accounts
                .stream()
                .filter(a -> a.equals(account))
                .findFirst();
    }
}
