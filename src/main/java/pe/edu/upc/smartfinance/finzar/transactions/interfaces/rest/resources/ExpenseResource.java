package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources;

public record ExpenseResource(
        Long id,
        SimplifiedWalletResource wallet,
        String categoryName,
        String periodRecurrenceName
) {
}
