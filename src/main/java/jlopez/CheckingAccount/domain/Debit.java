package jlopez.CheckingAccount.domain;

public class Debit extends Transaction {
    public Debit(Amount amount, Description description) {
        super(amount, description);
    }

}
