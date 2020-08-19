package jlopez.Customer.domain;

import java.util.UUID;

public class CustomerId extends ValueObject<UUID>  {
    public CustomerId(UUID value) {
        super(value);
    }
}
