package jlopez.shared.domain.valueObjects;

import java.util.Objects;
import java.util.UUID;

public class IdValueObject {
    private UUID value;

    public IdValueObject(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdValueObject that = (IdValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
