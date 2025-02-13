package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources;


import java.util.Date;

public record TransactionResource(
        Long id,
        SimplifiedWalletResource wallet,
        TransactionTypeResource type,
        String note,
        Double amount,
        Date date
) {
}
