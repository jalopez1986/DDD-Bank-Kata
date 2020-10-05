package core.CheckingAccount.domain.valueObjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class OpeningDate {
    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    private LocalDate value;

    public OpeningDate(LocalDate value) {
        this.value = value;
    }

    public String asString() {
        return value.format(DateTimeFormatter.ofPattern(DD_MM_YYYY));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpeningDate that = (OpeningDate) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
