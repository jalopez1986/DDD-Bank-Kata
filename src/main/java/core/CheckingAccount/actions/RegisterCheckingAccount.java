package core.CheckingAccount.actions;

import core.CheckingAccount.domain.CheckingAccount;
import core.CheckingAccount.domain.valueObjects.CheckingAccountId;
import core.CheckingAccount.domain.CheckingAccounts;
import core.CheckingAccount.domain.valueObjects.OpeningDate;
import core.Customer.domain.valueObjects.CustomerId;
import core.shared.domain.Clock;

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
