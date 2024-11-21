package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources.SimplifiedWalletResource;

public record ExpenseResource(
        Long id,
        SimplifiedWalletResource wallet,
        String categoryName,
        String periodRecurrenceName
) {
}
