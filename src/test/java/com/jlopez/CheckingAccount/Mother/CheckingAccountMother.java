package com.jlopez.CheckingAccount.Mother;

import jlopez.CheckingAccount.domain.Amount;
import jlopez.CheckingAccount.domain.CheckingAccount;
import jlopez.CheckingAccount.domain.CheckingAccountId;
import jlopez.CheckingAccount.domain.OpeningDate;
import jlopez.Customer.domain.CustomerId;

import java.time.LocalDate;

public class CheckingAccountMother {
    public static CheckingAccount withIds(CheckingAccountId id, CustomerId customerId) {
        return new CheckingAccount(id,
                customerId,
                anyOpeningDate(),
                DebitsMother.empty());
    }


    public static CheckingAccount withIdsAndOneDebitOf(CheckingAccountId id, CustomerId customerId, Amount amount) {
        return new CheckingAccount(id,
                customerId,
                anyOpeningDate(),
                DebitsMother.oneDebitOf(amount)
                );
    }


    private static OpeningDate anyOpeningDate() {
        return new OpeningDate(LocalDate.now());
    }

}
