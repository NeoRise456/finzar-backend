package pe.edu.upc.smartfinance.finzar.savings.domain.model.valueobjects;

import java.time.LocalDate;

public record SavingPeriod(LocalDate startDate, LocalDate endDate) {
    public SavingPeriod {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start and end dates must not be null");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must not be before start date");
        }
    }
}