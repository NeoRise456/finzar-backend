package pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands;

import java.time.LocalDateTime;

public record CreateTransactionCommand(
        Long walletId,
        Long transactionTypeId,
        String note,
        Double amount,
        LocalDateTime transactionDate
) {
}
