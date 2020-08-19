package jlopez.CheckingAccount.domain;

public interface CheckingAccounts {
    void save(CheckingAccount account);
    CheckingAccount findById(CheckingAccountId id);
}
