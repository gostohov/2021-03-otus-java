package ru.gostohov;

import ru.gostohov.domain.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ru.gostohov.enumiration.Currency.RUB;

public class Main {
    public static void main(java.lang.String[] args) throws ParseException {

        List<DepositSlot> depositSlots = new ArrayList<>();
        depositSlots.add(new DepositSlot(RUB, 100, 50));
        depositSlots.add(new DepositSlot(RUB, 500, 10));
        depositSlots.add(new DepositSlot(RUB, 1000, 5));
        depositSlots.add(new DepositSlot(RUB, 5000, 8));

        ATM atm = new ATM(new Bank("Tinkoff"), depositSlots);
        Card card = createCard();
        List<Cash> cashRUB = createCash();

        atm.insertCard(card);
        atm.enterPIN(6464);
        atm.printBalance();
        atm.withdrawByLarge(22500);
        atm.depositCash(cashRUB);
        atm.exit();
    }

    private static Card createCard() throws ParseException {
        java.lang.String sDate1="31/12/2025";
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        Date cardExpirationDate = formatter1.parse(sDate1);
        return new Card(
                "1234 5678 9012 3456",
                "John Doe",
                cardExpirationDate,
                797,
                6464
        );
    }

    private static List<Cash> createCash() {
        List<Cash> cashRUB = new ArrayList<>();
        cashRUB.add(new Cash(RUB, 100));
        cashRUB.add(new Cash(RUB, 100));
        cashRUB.add(new Cash(RUB, 500));
        cashRUB.add(new Cash(RUB, 500));
        cashRUB.add(new Cash(RUB, 1000));
        cashRUB.add(new Cash(RUB, 1000));
        cashRUB.add(new Cash(RUB, 5000));
        cashRUB.add(new Cash(RUB, 5000));
        return cashRUB;
    }
}
