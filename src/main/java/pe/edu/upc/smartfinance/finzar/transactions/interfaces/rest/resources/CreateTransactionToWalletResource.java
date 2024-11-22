package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources;

import java.util.Date;

public record CreateTransactionToWalletResource(
        Long sourceWalletId,
        String note,
        Double amount,
        Date transactionDate,
        Long destinationWalletId
) {
}
