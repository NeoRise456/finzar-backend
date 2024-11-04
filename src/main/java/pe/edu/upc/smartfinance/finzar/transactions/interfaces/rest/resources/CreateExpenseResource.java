package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources;

public record CreateExpenseResource(
        Double amount,
        Long walletId,
        Long categoryId,
        Long periodRecurrenceId
) {
}
