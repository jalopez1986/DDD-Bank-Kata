package jlopez.CheckingAccount.domain;

public class Credit extends Transaction {
    public Credit(Amount amount, Description description) {
        super(amount, description);
    }
}
