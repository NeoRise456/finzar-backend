package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.CreateIncomeCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.resources.CreateIncomeResource;

public class CreateIncomeCommandFromResourceAssembler {
    public static CreateIncomeCommand toCommandFromResource(CreateIncomeResource resource) {
        return new CreateIncomeCommand(resource.walletId(),
            resource.amount(),
            resource.categoryId(),
            resource.periodRecurrence()
        );
    }
}
