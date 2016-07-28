package com.example;

public class Account {
    private final String login;
    private final String password;
    private double balance;

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
        this.balance = 0;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (login != null ? !login.equals(account.login) : account.login != null) return false;
        return password != null ? password.equals(account.password) : account.password == null;

    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
