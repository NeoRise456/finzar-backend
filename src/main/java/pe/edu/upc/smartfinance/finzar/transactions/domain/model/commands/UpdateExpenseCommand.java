package pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands;

public record UpdateExpenseCommand(
        Long ExpenseId,
        Double amount,
        Long categoryId,
        Long periodRecurrenceId
) {
}
