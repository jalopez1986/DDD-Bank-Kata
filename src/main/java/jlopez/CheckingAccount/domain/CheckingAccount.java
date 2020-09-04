package jlopez.CheckingAccount.domain;

import jlopez.CheckingAccount.domain.valueObjects.Amount;
import jlopez.CheckingAccount.domain.valueObjects.CheckingAccountId;
import jlopez.CheckingAccount.domain.valueObjects.Description;
import jlopez.CheckingAccount.domain.valueObjects.OpeningDate;
import jlopez.Customer.domain.valueObjects.CustomerId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CheckingAccount {
    private final CheckingAccountId checkingAccountId;
    private final CustomerId customerId;
    private final OpeningDate openingDate;
    private State state;

    private List<Debit> debits;
    private List<Credit> credits;

    public CheckingAccount(CheckingAccountId checkingAccountId, CustomerId customerId, OpeningDate openingDate, List<Debit> debits, List<Credit> credits, State state) {
        this.checkingAccountId = checkingAccountId;
        this.customerId = customerId;
        this.openingDate = openingDate;
        this.debits = debits;
        this.credits = credits;
        this.state = state;
    }

    public static CheckingAccount createNewAccount(CheckingAccountId checkingAccountId, CustomerId customerId, OpeningDate openingDate) {
        return new CheckingAccount(checkingAccountId, customerId, openingDate, new ArrayList<>(), new ArrayList<>(), State.OPEN);
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


    //TODO jlopez: Preguntar en kata estos get me toco crearlos para almacenar en firebase

    public List<Debit> getDebits() {
        return debits;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckingAccount that = (CheckingAccount) o;
        return Objects.equals(checkingAccountId, that.checkingAccountId) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(openingDate, that.openingDate) &&
                state == that.state &&
                Objects.equals(debits, that.debits) &&
                Objects.equals(credits, that.credits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkingAccountId, customerId, openingDate, state, debits, credits);
    }

    public class CannotCloseTheCheckingAccount extends RuntimeException {
    }

    public class CannotWithdrawMoreThanTheExistingFunds extends RuntimeException {

    }
}
