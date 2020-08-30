package jlopez.shared.domain.valueObjects;

import java.util.UUID;

public class IdValueObject {
    private UUID value;

    public IdValueObject(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return this.value;
    }
}
