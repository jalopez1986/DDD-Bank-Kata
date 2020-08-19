package jlopez.CheckingAccount.domain;

import jlopez.Customer.domain.ValueObject;

import java.util.UUID;

public class CheckingAccountId extends ValueObject<UUID> {
    public CheckingAccountId(UUID value) {
        super(value);
    }
}
