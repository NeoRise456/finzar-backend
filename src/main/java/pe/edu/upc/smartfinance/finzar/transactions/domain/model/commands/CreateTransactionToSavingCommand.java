package pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands;

import java.util.Date;

public record CreateTransactionToSavingCommand(
        Long sourceWalletId,
        String note,
        Double amount,
        Date transactionDate,
        Long destinationSavingId
)
{
}
