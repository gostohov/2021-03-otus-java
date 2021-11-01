package ru.gostohov.domain;

import java.util.Date;

public class Card {
    private String cardNumber;
    private String cardholderName;
    private Date expirationDate;
    private int cvc;
    private int pinCode;

    public Card(String cardNumber, String cardholderName, Date expirationDate, int cvc, int pinCode) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.pinCode = pinCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
}
