package jlopez.CheckingAccount.domain;

public class Transaction {
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
