package pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands;

public record UpdateEarningCommand(
        Long EarningId,
        Double amount,
        Long categoryId,
        Long periodRecurrenceId
) {
}
