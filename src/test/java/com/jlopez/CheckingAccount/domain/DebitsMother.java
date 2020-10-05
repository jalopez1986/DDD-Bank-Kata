package com.jlopez.CheckingAccount.domain;

import core.CheckingAccount.domain.valueObjects.Amount;
import core.CheckingAccount.domain.Debit;
import core.CheckingAccount.domain.valueObjects.Description;

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
