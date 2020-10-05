package core.CheckingAccount.domain;

import core.CheckingAccount.domain.valueObjects.Amount;
import core.CheckingAccount.domain.valueObjects.Description;

import java.util.Objects;

public abstract class Transaction {
    protected Amount amount;
    protected Description description;

    public Transaction(Amount amount, Description description) {
        this.amount = amount;
        this.description = description;
    }

    public int getAmount() {
        return this.amount.getValue();
    }

    public Description getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, description);
    }
}
