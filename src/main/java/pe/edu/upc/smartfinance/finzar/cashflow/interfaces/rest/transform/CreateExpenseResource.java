package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform;

public record CreateExpenseResource(
        Double amount,
        Long walletId,
        Long categoryId,
        Long periodRecurrenceId
) {
}
