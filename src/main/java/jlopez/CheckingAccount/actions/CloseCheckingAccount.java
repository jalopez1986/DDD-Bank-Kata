package jlopez.CheckingAccount.actions;

import jlopez.CheckingAccount.domain.CheckingAccount;
import jlopez.CheckingAccount.domain.valueObjects.CheckingAccountId;
import jlopez.CheckingAccount.domain.CheckingAccounts;

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
