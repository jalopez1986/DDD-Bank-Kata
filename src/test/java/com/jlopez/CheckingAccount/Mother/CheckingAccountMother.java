package com.jlopez.CheckingAccount.Mother;

import jlopez.CheckingAccount.domain.valueObjects.Amount;
import jlopez.CheckingAccount.domain.CheckingAccount;
import jlopez.CheckingAccount.domain.valueObjects.CheckingAccountId;
import jlopez.CheckingAccount.domain.valueObjects.OpeningDate;
import jlopez.Customer.domain.valueObjects.CustomerId;

import java.time.LocalDate;
import java.util.ArrayList;

public class CheckingAccountMother {
    public static CheckingAccount withIds(CheckingAccountId id,
                                          CustomerId customerId) {
        return new CheckingAccount(id,
                customerId,
                anyOpeningDate(),
                DebitsMother.empty(),
                new ArrayList<>());
    }


    public static CheckingAccount withIdsAndOneDebitOf(CheckingAccountId id,
                                                       CustomerId customerId,
                                                       Amount amount) {
        return new CheckingAccount(id,
                customerId,
                anyOpeningDate(),
                DebitsMother.oneDebitOf(amount),
                new ArrayList<>()
        );
    }


    public static CheckingAccount withIdsAndOneDebitOfAndOneCreditOf(CheckingAccountId id,
                                                                     CustomerId customerId,
                                                                     Amount debitAmount,
                                                                     Amount creditAmount) {
        return new CheckingAccount(id,
                customerId,
                anyOpeningDate(),
                DebitsMother.oneDebitOf(debitAmount),
                CreditsMother.oneCreditOf(creditAmount)
        );
    }


    private static OpeningDate anyOpeningDate() {
        return new OpeningDate(LocalDate.now());
    }

}
