package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.CreateEarningCommand;

public class CreateEarningCommandFromResourceAssembler {
    public static CreateEarningCommand toCommandFromResource(CreateEarningResource resource) {
        return new CreateEarningCommand(resource.walletId(),
            resource.amount(),
            resource.categoryId(),
            resource.periodRecurrenceId()
        );
    }
}
