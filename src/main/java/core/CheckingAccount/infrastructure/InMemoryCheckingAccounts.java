package core.CheckingAccount.infrastructure;

import core.CheckingAccount.domain.CheckingAccount;
import core.CheckingAccount.domain.valueObjects.CheckingAccountId;
import core.CheckingAccount.domain.CheckingAccounts;

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
