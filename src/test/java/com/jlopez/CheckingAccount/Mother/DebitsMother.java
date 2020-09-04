package com.jlopez.CheckingAccount.Mother;

import jlopez.CheckingAccount.domain.valueObjects.Amount;
import jlopez.CheckingAccount.domain.Debit;
import jlopez.CheckingAccount.domain.valueObjects.Description;

import java.util.ArrayList;
import java.util.List;

public class DebitsMother {
    public static List<Debit> empty() {
        return new ArrayList<>();
    }

    public static List<Debit> oneDebitOf(Amount amount) {
        List<Debit> debits = new ArrayList<>();
        debits.add(new Debit(amount, anyDescription()));

        return debits;
    }

    private static Description anyDescription() {
        return new Description("ANY_DESCRIPTION");
    }

    public static List<Debit> randomDebits() {
        List<Debit> debits = new ArrayList<>();
        debits.add(new Debit(new Amount(100), anyDescription()));
        debits.add(new Debit(new Amount(200), anyDescription()));

        return debits;
    }

}
