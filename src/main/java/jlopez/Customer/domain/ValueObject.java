package jlopez.Customer.domain;

import java.util.Objects;

public abstract class ValueObject<T> {
    private T value;

    public ValueObject(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueObject<?> that = (ValueObject<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
