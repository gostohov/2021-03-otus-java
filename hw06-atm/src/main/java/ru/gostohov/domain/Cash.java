package ru.gostohov.domain;

import ru.gostohov.enumiration.Currency;

public class Cash {
    private Currency currency;
    private int value;

    public Cash(Currency currency, int value) {
        this.currency = currency;
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Cash{" +
                "currency=" + currency +
                ", value=" + value +
                '}';
    }
}

