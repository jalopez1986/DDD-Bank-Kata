package jlopez.CheckingAccount.domain;

import jlopez.Customer.domain.CustomerId;

public class CheckingAccount {
    private final CheckingAccountId checkingAccountId;
    private final CustomerId customerId;

    public CheckingAccount(CheckingAccountId checkingAccountId, CustomerId customerId) {
        this.checkingAccountId = checkingAccountId;
        this.customerId = customerId;
    }

    public CheckingAccountId getCheckingAccountId() {
        return checkingAccountId;
    }

}
