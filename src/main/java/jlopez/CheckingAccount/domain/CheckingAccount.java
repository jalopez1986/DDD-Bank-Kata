package jlopez.CheckingAccount.domain;

import jlopez.Customer.domain.CustomerId;

import java.util.ArrayList;
import java.util.List;

public class CheckingAccount {
    private final CheckingAccountId checkingAccountId;
    private final CustomerId customerId;

    private List<Debit> debits;


    public CheckingAccount(CheckingAccountId checkingAccountId, CustomerId customerId) {
        this.checkingAccountId = checkingAccountId;
        this.customerId = customerId;
        this.debits = new ArrayList<>();
    }

    public CheckingAccountId getCheckingAccountId() {
        return checkingAccountId;
    }

    public void deposit(int amount) {
        debits.add(new Debit(amount));
    }

    public int getBalance() {
        return debits.stream().mapToInt(debit->debit.getAmount()).sum();
    }
}
