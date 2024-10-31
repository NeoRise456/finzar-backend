package pe.edu.upc.smartfinance.finzar.savingsgraphics.domain.model.commands;

import java.time.LocalDate;

public record GetSavingsGraphicsCommand(
        Long id,
        LocalDate startDate,
        LocalDate endDate
) {
    public GetSavingsGraphicsCommand {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
    }
}