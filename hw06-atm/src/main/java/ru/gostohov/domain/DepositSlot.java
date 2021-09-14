package ru.gostohov.domain;

import ru.gostohov.enumiration.Currency;

public class DepositSlot {
    private Currency currency;
    private int size;
    private int balance;

    public DepositSlot(Currency currency, int size, int balance) {
        this.currency = currency;
        this.size = size;
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void addCash(int cash) {
        this.balance += cash;
    }
}
