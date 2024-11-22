package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.resources;

public record CreateIncomeResource(
        Double amount,
        Long walletId,
        Long categoryId,
        String periodRecurrence
) {
}
