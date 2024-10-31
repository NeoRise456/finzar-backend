package pe.edu.upc.smartfinance.finzar.savings.domain.model.commands;

import java.time.LocalDate;

public record CreateSavingCommand(
        Long userId,
        String name,
        double totalGoal,
        double currentAmount,
        Long categoryId,
        LocalDate startDate,
        LocalDate endDate
) {
}