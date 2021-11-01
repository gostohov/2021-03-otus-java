package ru.gostohov.domain;

import ru.gostohov.enumiration.Currency;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ATM {
    private Card card;
    private boolean pinValid = false;
    private Account account;
    private Bank bank;
    private List<DepositSlot> depositSlots;

    public ATM(Bank bank, List<DepositSlot> depositSlots) {
        this.bank = bank;
        this.depositSlots = depositSlots;
    }

    public void insertCard(Card card) {
        this.card = card;
    }

    public void enterPIN(int pin) {
        System.out.println("Please enter pin code!");

        if (this.card == null) {
            System.out.println("Please insert card to the card reader!");
            return;
        }

        if (this.card.getPinCode() != pin) {
            System.out.println("Entered pin code is not correct!");
        } else {
            System.out.println("Entered pin code is correct");
            System.out.println("Hello, " + this.card.getCardholderName() + "!");
            this.pinValid = true;
            this.account = this.bank.getAccount(this.card.getCardNumber(), this.card.getCardholderName());
        }
    }

    public void printBalance() {
        System.out.println("Your account balance is: " + this.account.getBalance());
    }

    public void withdrawByLarge(int amount) {
        var safeAmount = amount;
        var balance = this.account.getBalance();
        if (balance - amount < 0) {
            System.out.println("There are not enough funds on your account!");
        }

        var fullyDivided = false;
        var errorOccured = false;
        var maxCurrencySize = getMaxDepositSlotSize(0);
        List<Cash> cash = new ArrayList<>();

        while(!fullyDivided || !errorOccured) {
            if (amount == 0) {
                fullyDivided = true;
                break;
            }

            if (amount < maxCurrencySize) {
                maxCurrencySize = getMaxDepositSlotSize(maxCurrencySize);
            }

            var chunks = amount / maxCurrencySize;
            if (chunks == 0) {
                System.out.println("Error! ATM cannot dispense the specified amount in large bills!");
                errorOccured = true;
                break;
            }

            var chunkCash = chunks * maxCurrencySize;
            amount -= chunkCash;
            for (int i = 0; i < chunks; i++) {
                cash.add(new Cash(Currency.RUB, maxCurrencySize));
            }
        }

        if (fullyDivided) {
            this.account.setBalance(this.account.getBalance() - safeAmount);
            System.out.println("Take your money!" + cash.toString());
            System.out.println("You have been successfully withdraw money from your bank account! Your account balance is: " + this.account.getBalance());

        }
    }

    public void depositCash(List<Cash> cashRUB) {
        if (!checkCashValidity(cashRUB)) {
            return;
        }

        for (Cash cash : cashRUB) {
            var cashSize = cash.getValue();
            var depositSlot = this.depositSlots.stream()
                    .filter(slot -> slot.getSize() == cashSize)
                    .findAny()
                    .orElse(null);

            depositSlot.addCash(cashSize);
            this.account.addCash(cashSize);
        }

        System.out.println("You have been successfully added money to your bank account! Your account balance is: " + this.account.getBalance());
    }

    public void exit() {
        this.card = null;
        this.pinValid = false;
    }

    private int getMaxDepositSlotSize(int max) {
        return this.depositSlots.stream()
                .filter(slot -> max > 0 ? slot.getBalance() > 0 && slot.getSize() < max : slot.getBalance() > 0)
                .max(Comparator.comparing(DepositSlot::getSize))
                .orElseThrow(NoSuchFieldError::new).getSize();
    }

    private boolean checkCashValidity(List<Cash> cashList) {
        for (Cash cash : cashList) {
            var cashSize = cash.getValue();
            var depositSlot = this.depositSlots.stream()
                    .filter(slot -> slot.getSize() == cashSize)
                    .findAny()
                    .orElse(null);

            if (depositSlot == null) {
                System.out.println("ATM cannot accept banknotes of this denomination! " + cashSize);
                return false;
            }
        }

        return true;
    }
}
