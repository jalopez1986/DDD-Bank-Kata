package core.CheckingAccount.actions;

import core.CheckingAccount.domain.CheckingAccount;
import core.CheckingAccount.domain.valueObjects.CheckingAccountId;
import core.CheckingAccount.domain.CheckingAccounts;

public class CloseCheckingAccount {
    private CheckingAccounts checkingAccounts;

    public CloseCheckingAccount(CheckingAccounts checkingAccounts) {
        this.checkingAccounts = checkingAccounts;
    }

    public void execute(CheckingAccountId checkingAccountId) {
        CheckingAccount checkingAccount = checkingAccounts.findById(checkingAccountId);
        checkingAccount.close();

        checkingAccounts.save(checkingAccount);
    }
}
