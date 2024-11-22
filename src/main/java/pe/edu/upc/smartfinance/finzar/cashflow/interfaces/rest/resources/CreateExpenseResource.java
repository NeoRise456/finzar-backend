package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.resources;

public record CreateExpenseResource(
        Double amount,
        Long walletId,
        Long categoryId,
        Long periodRecurrenceId
) {
}
