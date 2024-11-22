package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources;

import java.util.Date;

public record CreateCashflowTransactionResource(
        Long sourceWalletId,
        String note,
        Date transactionDate
) {
}
