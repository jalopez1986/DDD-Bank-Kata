package com.jlopez.CheckingAccount.Mother;

import jlopez.CheckingAccount.domain.Amount;
import jlopez.CheckingAccount.domain.Debit;
import jlopez.CheckingAccount.domain.Description;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DebitsMother {
    public static List<Debit> empty() {
        return Collections.emptyList();
    }

    public static List<Debit> oneDebitOf(Amount amount) {
        List<Debit> debits = new ArrayList<>();
        debits.add(new Debit(amount, anyDescription()));

        return debits;
    }

    private static Description anyDescription() {
        return new Description("ANY_DESCRIPTION");
    }
}
