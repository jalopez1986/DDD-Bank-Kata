package jlopez.CheckingAccount.domain;

public class Debit {
    private Amount amount;
    private Description description;

    public Debit(Amount amount, Description description) {
        this.amount = amount;
        this.description = description;
    }

    public int getAmount() {
        return this.amount.getValue();
    }
}
