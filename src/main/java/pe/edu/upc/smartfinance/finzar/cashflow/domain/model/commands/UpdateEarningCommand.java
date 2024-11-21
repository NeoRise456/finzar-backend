package pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands;

public record UpdateEarningCommand(
        Long EarningId,
        Double amount,
        Long categoryId,
        Long periodRecurrenceId
) {
}
