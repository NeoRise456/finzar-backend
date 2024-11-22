package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.resources;

public record CreateEarningResource(
        Double amount,
        Long walletId,
        Long categoryId,
        Long periodRecurrenceId
) {
}
