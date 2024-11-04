package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CreateTransactionResource(
        Long walletId,
        Long transactionTypeId,
        String note,
        Double amount,
        LocalDateTime transactionDate
) {
}
