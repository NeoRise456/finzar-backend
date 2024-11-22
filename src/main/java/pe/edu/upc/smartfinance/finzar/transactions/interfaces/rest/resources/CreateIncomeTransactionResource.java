package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources;

import java.util.Date;

public record CreateIncomeTransactionResource(
        Long sourceWalletId,
        String note,
        Double amount,
        Date transactionDate
) {
}
