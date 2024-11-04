package pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands;

public record CreateEarningCommand(
        Long walletId,
        Double amount,
        Long categoryId,
        Long periodRecurrenceId
) {
}
