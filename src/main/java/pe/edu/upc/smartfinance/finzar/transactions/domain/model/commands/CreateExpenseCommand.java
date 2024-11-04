package pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands;

public record CreateExpenseCommand(
        Double amount,
        Long categoryId,
        Long periodRecurrenceId,
        Long transactionId
) {
}
