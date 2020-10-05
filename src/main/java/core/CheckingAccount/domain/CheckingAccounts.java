package core.CheckingAccount.domain;

import core.CheckingAccount.domain.valueObjects.CheckingAccountId;


public interface CheckingAccounts {
    void save(CheckingAccount account);
    CheckingAccount findById(CheckingAccountId id);

}
