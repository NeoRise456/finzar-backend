package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.CreateTransactionToWalletCommand;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources.CreateTransactionToWalletResource;

public class CreateTransactionToWalletCommandFromResourceAssembler {
    public static CreateTransactionToWalletCommand toCommandFromResource(CreateTransactionToWalletResource resource) {
        return new CreateTransactionToWalletCommand(
                resource.sourceWalletId(),
                resource.note(),
                resource.amount(),
                resource.transactionDate(),
                resource.destinationWalletId()
        );
    }
}
