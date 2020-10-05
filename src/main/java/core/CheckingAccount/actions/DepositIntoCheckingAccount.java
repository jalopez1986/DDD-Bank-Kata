package core.CheckingAccount.actions;

import core.CheckingAccount.domain.*;
import core.CheckingAccount.domain.valueObjects.Amount;
import core.CheckingAccount.domain.valueObjects.CheckingAccountId;
import core.CheckingAccount.domain.valueObjects.Description;

public class DepositIntoCheckingAccount {
    private final CheckingAccounts checkingAccounts;

    public DepositIntoCheckingAccount(CheckingAccounts checkingAccounts) {
        this.checkingAccounts = checkingAccounts;
    }

    public void execute(CheckingAccountId checkingAccountId, Amount amount, Description description) {
        CheckingAccount checkingAccount = checkingAccounts.findById(checkingAccountId);
        checkingAccount.deposit(amount, description);

        checkingAccounts.save(checkingAccount);
    }
}
