package pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands;

import java.util.Date;

public record CreateTransactionEarningCommand(
        Long walletId,
        Long transactionTypeId,
        String note,
        Double amount,
        Date transactionDate,
        Long earningId
) {
}
