package com.jlopez.CheckingAccount.domain;

import core.CheckingAccount.domain.valueObjects.Amount;
import core.CheckingAccount.domain.Credit;
import core.CheckingAccount.domain.valueObjects.Description;

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

    public static List<Credit> randomCredits() {
        List<Credit> credits = new ArrayList<>();
        credits.add(new Credit(new Amount(200), anyDescription()));
        credits.add(new Credit(new Amount(100), anyDescription()));

        return credits;


    }
}
