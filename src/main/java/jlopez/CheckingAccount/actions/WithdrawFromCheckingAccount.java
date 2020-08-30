package jlopez.CheckingAccount.actions;

import jlopez.CheckingAccount.domain.*;

public class WithdrawFromCheckingAccount {
    private CheckingAccounts checkingAccounts;

    public WithdrawFromCheckingAccount(CheckingAccounts checkingAccounts) {
        this.checkingAccounts = checkingAccounts;
    }

    public void execute(CheckingAccountId checkingAccountId, Amount amount, Description description) {
        CheckingAccount checkingAccount = checkingAccounts.findById(checkingAccountId);
        checkingAccount.withdraw(amount, description);

    }
}
