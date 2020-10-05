package com.jlopez.CheckingAccount.domain;

import core.CheckingAccount.domain.valueObjects.Amount;
import core.CheckingAccount.domain.CheckingAccount;
import core.CheckingAccount.domain.valueObjects.CheckingAccountId;
import core.CheckingAccount.domain.valueObjects.OpeningDate;
import core.Customer.domain.valueObjects.CustomerId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class CheckingAccountMother {
    public static CheckingAccount withIds(CheckingAccountId id,
                                          CustomerId customerId) {
        return new CheckingAccount(id,
                customerId,
                anyOpeningDate(),
                DebitsMother.empty(),
                new ArrayList<>(),
                CheckingAccount.State.OPEN
        );
    }


    public static CheckingAccount withIdsAndOneDebitOf(CheckingAccountId id,
                                                       CustomerId customerId,
                                                       Amount amount) {
        return new CheckingAccount(id,
                customerId,
                anyOpeningDate(),
                DebitsMother.oneDebitOf(amount),
                new ArrayList<>(),
                CheckingAccount.State.OPEN
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
                CreditsMother.oneCreditOf(creditAmount),
                CheckingAccount.State.OPEN
        );
    }


    public static CheckingAccount randomCheckingAccount() {
        return new CheckingAccount(anyCheckingAccountId(),
                anyCustomerId(),
                anyOpeningDate(),
                DebitsMother.empty(),
                CreditsMother.empty(),
                CheckingAccount.State.OPEN
        );
    }

    public static CheckingAccount randomCheckingAccountsWithDebitsAndCredits() {
        return new CheckingAccount(anyCheckingAccountId(),
                anyCustomerId(),
                anyOpeningDate(),
                DebitsMother.randomDebits(),
                CreditsMother.randomCredits(),
                CheckingAccount.State.OPEN
        );
    }




    private static OpeningDate anyOpeningDate() {
        return new OpeningDate(LocalDate.now());
    }

    private static CheckingAccountId anyCheckingAccountId() {
        return new CheckingAccountId(UUID.randomUUID());
    }

    private static CustomerId anyCustomerId() {
        return new CustomerId(UUID.randomUUID());
    }

}
