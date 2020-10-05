package core.CheckingAccount.actions;

import core.CheckingAccount.domain.*;
import core.CheckingAccount.domain.valueObjects.Amount;
import core.CheckingAccount.domain.valueObjects.CheckingAccountId;
import core.CheckingAccount.domain.valueObjects.Description;

public class WithdrawFromCheckingAccount {
    private CheckingAccounts checkingAccounts;

    public WithdrawFromCheckingAccount(CheckingAccounts checkingAccounts) {
        this.checkingAccounts = checkingAccounts;
    }

    public void execute(CheckingAccountId checkingAccountId, Amount amount, Description description) {
        CheckingAccount checkingAccount = checkingAccounts.findById(checkingAccountId);
        checkingAccount.withdraw(amount, description);

        checkingAccounts.save(checkingAccount);

    }
}
