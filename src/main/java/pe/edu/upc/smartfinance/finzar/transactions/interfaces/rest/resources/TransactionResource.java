package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources;

import java.time.LocalDateTime;

public record TransactionResource(
        Long id,
        SimplifiedWalletResource wallet,
        TransactionTypeResource type,
        String note,
        Double amount,
        LocalDateTime date
) {
}
