package jlopez.CheckingAccount.actions;

import jlopez.CheckingAccount.domain.*;
import jlopez.Customer.domain.Customers;

public class DepositIntoCheckingAccount {
    private final Customers customers;
    private final CheckingAccounts checkingAccounts;

    public DepositIntoCheckingAccount(Customers customers, CheckingAccounts checkingAccounts) {

        this.customers = customers;
        this.checkingAccounts = checkingAccounts;
    }

    public void execute(CheckingAccountId checkingAccountId, Amount amount, Description description) {
        CheckingAccount checkingAccount = checkingAccounts.findById(checkingAccountId);
        checkingAccount.deposit(amount, description);
    }
}
