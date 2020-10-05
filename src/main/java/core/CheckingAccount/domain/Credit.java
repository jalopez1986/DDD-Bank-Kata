package core.CheckingAccount.domain;

import core.CheckingAccount.domain.valueObjects.Amount;
import core.CheckingAccount.domain.valueObjects.Description;

public class Credit extends Transaction {
    public Credit(Amount amount, Description description) {
        super(amount, description);
    }
}
