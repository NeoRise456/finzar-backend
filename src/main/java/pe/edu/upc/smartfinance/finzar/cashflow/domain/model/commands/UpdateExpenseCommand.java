package pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands;

public record UpdateExpenseCommand(
        Long ExpenseId,
        Double amount,
        Long categoryId,
        Long periodRecurrenceId
) {
}
