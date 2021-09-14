package ru.gostohov.domain;

public class Account {
    private String id;
    private String holderName;
    private int balance;

    public Account(String id, String holderName, int balance) {
        this.id = id;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
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
