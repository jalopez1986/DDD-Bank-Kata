package jlopez.CheckingAccount.domain;

public class Debit {
    private int amount;

    public Debit(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }
}
