package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.resources;

import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources.SimplifiedWalletResource;

public record IncomeResource(
        Long id,
        SimplifiedWalletResource wallet,
        String categoryName,
        String periodRecurrenceName
) {
}
