package pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands;

public record CreateExpenseCommand(
        Double amount,
        Long walletId,
        Long categoryId,
        String periodRecurrence
) {
}
