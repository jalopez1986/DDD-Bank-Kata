package jlopez.CheckingAccount.domain;

import jlopez.CheckingAccount.domain.valueObjects.Amount;
import jlopez.CheckingAccount.domain.valueObjects.Description;

public class Credit extends Transaction {
    public Credit(Amount amount, Description description) {
        super(amount, description);
    }
}
