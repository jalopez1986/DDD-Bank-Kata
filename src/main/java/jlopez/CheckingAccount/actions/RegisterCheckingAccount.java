package jlopez.CheckingAccount.actions;

import jlopez.CheckingAccount.domain.CheckingAccount;
import jlopez.CheckingAccount.domain.valueObjects.CheckingAccountId;
import jlopez.CheckingAccount.domain.CheckingAccounts;
import jlopez.CheckingAccount.domain.valueObjects.OpeningDate;
import jlopez.Customer.domain.valueObjects.CustomerId;
import jlopez.shared.domain.Clock;

public class RegisterCheckingAccount {
    private final CheckingAccounts checkingAccounts;
    private Clock clock;

    public RegisterCheckingAccount(CheckingAccounts checkingAccounts, Clock clock) {
        this.checkingAccounts = checkingAccounts;
        this.clock = clock;
    }

    public void execute(CheckingAccountId checkingAccountId, CustomerId customerId) {
        CheckingAccount account = CheckingAccount.createNewAccount(checkingAccountId, customerId, new OpeningDate(clock.now()));
        checkingAccounts.save(account);
    }
}
