package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.CreateTransactionCommand;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources.CreateTransactionResource;

public class CreateTransactionCommandFromResourceAssembler {
    public static CreateTransactionCommand toCommandFromResource(CreateTransactionResource resource) {
        return new CreateTransactionCommand(
                resource.walletId(),
                resource.transactionTypeId(),
                resource.note(),
                resource.amount(),
                resource.transactionDate()
        );
    }
}
