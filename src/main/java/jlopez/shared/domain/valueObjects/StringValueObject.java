package jlopez.shared.domain.valueObjects;

public abstract class StringValueObject {
    private String value;

    public StringValueObject(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
