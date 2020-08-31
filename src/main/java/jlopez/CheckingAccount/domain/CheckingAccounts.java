package jlopez.CheckingAccount.domain;

import jlopez.CheckingAccount.domain.valueObjects.CheckingAccountId;

public interface CheckingAccounts {
    void save(CheckingAccount account);
    CheckingAccount findById(CheckingAccountId id);

}
