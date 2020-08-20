package jlopez.CheckingAccount.actions;

import jlopez.CheckingAccount.domain.CheckingAccount;
import jlopez.CheckingAccount.domain.CheckingAccountId;
import jlopez.CheckingAccount.domain.CheckingAccounts;
import jlopez.Customer.domain.Customers;

public class DepositIntoCheckingAccount {
    private final Customers customers;
    private final CheckingAccounts checkingAccounts;

    public DepositIntoCheckingAccount(Customers customers, CheckingAccounts checkingAccounts) {

        this.customers = customers;
        this.checkingAccounts = checkingAccounts;
    }

    public void execute(CheckingAccountId checkingAccountId, int amount) {
        CheckingAccount checkingAccount = checkingAccounts.findById(checkingAccountId);
        checkingAccount.deposit(amount);
    }
}
