package pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands;

public record CreateEarningCommand(
        Long walletId,
        Double amount,
        Long categoryId,
        Long periodRecurrenceId
) {
}
