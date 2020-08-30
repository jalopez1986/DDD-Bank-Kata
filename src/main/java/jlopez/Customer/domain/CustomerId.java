package jlopez.Customer.domain;

import jlopez.shared.domain.valueObjects.IdValueObject;

import java.util.UUID;

public class CustomerId extends IdValueObject {
    public CustomerId(UUID value) {
        super(value);
    }
}
