package jlopez.CheckingAccount.infrastructure;

import jlopez.CheckingAccount.domain.CheckingAccount;
import jlopez.CheckingAccount.domain.valueObjects.CheckingAccountId;
import jlopez.CheckingAccount.domain.CheckingAccounts;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryCheckingAccounts implements CheckingAccounts {
    private final Map<UUID, CheckingAccount> accounts = new HashMap<>();

    @Override
    public void save(CheckingAccount account) {
        accounts.put(account.getCheckingAccountId().getValue(), account);

    }

    @Override
    public CheckingAccount findById(CheckingAccountId id) {
        return accounts.get(id.getValue());
    }
}
