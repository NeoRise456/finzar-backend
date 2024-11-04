package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.CreateEarningCommand;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources.CreateEarningResource;

public class CreateEarningCommandFromResourceAssembler {
    public static CreateEarningCommand toCommandFromResource(CreateEarningResource resource) {
        return new CreateEarningCommand(resource.walletId(),
            resource.amount(),
            resource.categoryId(),
            resource.periodRecurrenceId()
        );
    }
}
