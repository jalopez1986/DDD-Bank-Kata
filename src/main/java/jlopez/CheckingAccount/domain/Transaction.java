package jlopez.CheckingAccount.domain;

import jlopez.CheckingAccount.domain.valueObjects.Amount;
import jlopez.CheckingAccount.domain.valueObjects.Description;

public abstract class Transaction {
    protected Amount amount;
    protected Description description;

    public Transaction(Amount amount, Description description) {
        this.amount = amount;
        this.description = description;
    }

    public int getAmount() {
        return this.amount.getValue();
    }
}
