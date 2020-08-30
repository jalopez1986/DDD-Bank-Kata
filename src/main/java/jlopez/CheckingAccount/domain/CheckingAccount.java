package jlopez.CheckingAccount.domain;

import jlopez.Customer.domain.CustomerId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckingAccount {
    private final CheckingAccountId checkingAccountId;
    private final CustomerId customerId;
    private final OpeningDate openingDate;

    private List<Debit> debits;

    public CheckingAccount(CheckingAccountId checkingAccountId, CustomerId customerId, OpeningDate openingDate, List<Debit> debits) {
        this.checkingAccountId = checkingAccountId;
        this.customerId = customerId;
        this.openingDate = openingDate;
        this.debits = new ArrayList<>();
    }

    public static CheckingAccount createNewAccount(CheckingAccountId checkingAccountId, CustomerId customerId, OpeningDate openingDate) {
        return  new CheckingAccount(checkingAccountId, customerId, openingDate, Collections.emptyList());
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public CheckingAccountId getCheckingAccountId() {
        return checkingAccountId;
    }

    public void deposit(Amount amount, Description description) {
        debits.add(new Debit(amount, description));
    }

    public int getBalance() {
        return debits.stream().mapToInt(debit->debit.getAmount()).sum();
    }

    public String getOpeningDateAsString() {
        return openingDate.asString();
    }
}
