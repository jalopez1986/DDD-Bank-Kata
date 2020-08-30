package jlopez.CheckingAccount.actions;

import jlopez.CheckingAccount.domain.*;

public class DepositIntoCheckingAccount {
    private final CheckingAccounts checkingAccounts;

    public DepositIntoCheckingAccount(CheckingAccounts checkingAccounts) {
        this.checkingAccounts = checkingAccounts;
    }

    public void execute(CheckingAccountId checkingAccountId, Amount amount, Description description) {
        CheckingAccount checkingAccount = checkingAccounts.findById(checkingAccountId);
        checkingAccount.deposit(amount, description);
    }
}
