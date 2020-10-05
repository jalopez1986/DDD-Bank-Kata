package core.CheckingAccount.domain;

import core.CheckingAccount.domain.valueObjects.Amount;
import core.CheckingAccount.domain.valueObjects.Description;

public class Debit extends Transaction {
    public Debit(Amount amount, Description description) {
        super(amount, description);
    }

}
