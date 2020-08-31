package jlopez.CheckingAccount.domain.valueObjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OpeningDate {
    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    private LocalDate value;

    public OpeningDate(LocalDate value) {
        this.value = value;
    }

    public String asString() {
        return value.format(DateTimeFormatter.ofPattern(DD_MM_YYYY));
    }
}
