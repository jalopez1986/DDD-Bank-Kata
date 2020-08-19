package jlopez.CheckingAccount.actions;

import jlopez.CheckingAccount.domain.CheckingAccount;
import jlopez.CheckingAccount.domain.CheckingAccountId;
import jlopez.CheckingAccount.domain.CheckingAccounts;
import jlopez.Customer.domain.CustomerId;
import jlopez.Customer.domain.Customers;

public class RegisterCheckingAccount {
    private final Customers customers;
    private final CheckingAccounts checkingAccounts;

    public RegisterCheckingAccount(Customers customers, CheckingAccounts checkingAccounts) {
        this.customers = customers;
        this.checkingAccounts = checkingAccounts;
    }

    public void execute(CheckingAccountId checkingAccountId, CustomerId customerId) {
        CheckingAccount account = new CheckingAccount(checkingAccountId, customerId);
        checkingAccounts.save(account);
    }
}
