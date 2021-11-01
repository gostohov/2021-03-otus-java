package ru.gostohov.domain;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<Account> accountList = new ArrayList<Account>();

    public Bank(String name) {
        this.name = name;
        this.accountList.add(
                new Account("1234 5678 9012 3456","John Doe",100_000)
        );
    }

    public Account getAccount(String id, String holderName) {
        return this.accountList.stream()
                .filter(account -> id.equals(account.getId()) && holderName.equals(account.getHolderName()))
                .findAny()
                .orElse(null);
    }
}
