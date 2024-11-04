package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources;

public record EarningResource(
        Long id,
        SimplifiedWalletResource wallet,
        String categoryName,
        String periodRecurrenceName
) {
}
