package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources;

public record CreateEarningResource(
        Double amount,
        Long walletId,
        Long categoryId,
        Long periodRecurrenceId
) {
}
