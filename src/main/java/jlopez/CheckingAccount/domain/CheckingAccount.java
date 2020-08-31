package jlopez.CheckingAccount.domain;

import jlopez.Customer.domain.CustomerId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckingAccount {
    private final CheckingAccountId checkingAccountId;
    private final CustomerId customerId;
    private final OpeningDate openingDate;
    private State state;

    private List<Debit> debits;
    private List<Credit> credits;

    public CheckingAccount(CheckingAccountId checkingAccountId, CustomerId customerId, OpeningDate openingDate, List<Debit> debits, List<Credit> credits) {
        this.checkingAccountId = checkingAccountId;
        this.customerId = customerId;
        this.openingDate = openingDate;
        this.debits = debits;
        this.credits = credits;
        this.state = State.OPEN;
    }

    public static CheckingAccount createNewAccount(CheckingAccountId checkingAccountId, CustomerId customerId, OpeningDate openingDate) {
        return new CheckingAccount(checkingAccountId, customerId, openingDate, new ArrayList<>(), new ArrayList<>());
    }

    public void deposit(Amount amount, Description description) {
        debits.add(new Debit(amount, description));
    }

    public void withdraw(Amount amount, Description description) {
        if (amount.getValue() > getBalance()) { throw new CannotWithdrawMoreThanTheExistingFunds(); }

        credits.add(new Credit(amount, description));
    }

    public void close() {
        if (getBalance() != 0) { throw new CannotCloseTheCheckingAccount(); }

        state = State.CLOSE;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public CheckingAccountId getCheckingAccountId() {
        return checkingAccountId;
    }

    public int getBalance() {
        int totalDebits = debits.stream().mapToInt(debit->debit.getAmount()).sum();
        int totalCredits = credits.stream().mapToInt(credit->credit.getAmount()).sum();
        return totalDebits - totalCredits;
    }

    public String getOpeningDateAsString() {
        return openingDate.asString();
    }

    public State getState() {
        return state;
    }

    public enum State {
        OPEN,
        CLOSE
    }

    public class CannotCloseTheCheckingAccount extends RuntimeException {
    }

    public class CannotWithdrawMoreThanTheExistingFunds extends RuntimeException {

    }




}
