package com.jlopez.CheckingAccount.Mother;

import jlopez.CheckingAccount.domain.valueObjects.Amount;
import jlopez.CheckingAccount.domain.Credit;
import jlopez.CheckingAccount.domain.valueObjects.Description;

import java.util.ArrayList;
import java.util.List;

public class CreditsMother {
    public static List<Credit> empty() {
        return new ArrayList<>();
    }

    public static List<Credit> oneCreditOf(Amount amount) {
        List<Credit> credits = new ArrayList<>();
        credits.add(new Credit(amount, anyDescription()));

        return credits;
    }

    private static Description anyDescription() {
        return new Description("ANY_DESCRIPTION");
    }
}
