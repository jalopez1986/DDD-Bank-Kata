package jlopez.CheckingAccount.domain;

import jlopez.CheckingAccount.domain.valueObjects.Amount;
import jlopez.CheckingAccount.domain.valueObjects.Description;

public class Debit extends Transaction {
    public Debit(Amount amount, Description description) {
        super(amount, description);
    }

}
