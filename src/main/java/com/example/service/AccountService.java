package com.example.service;

import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
public class AccountService {
    private final CopyOnWriteArraySet<Account> accounts = new CopyOnWriteArraySet<Account>();

    public boolean register(Account account) {
        return this.accounts.add(account);
    }

    public Optional<Account> byLogin(String login) {
        return this.accounts
                .stream()
                .filter(a -> Objects.equals(a.getLogin(), login))
                .findFirst();
    }
}
