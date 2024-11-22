package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.CreateExpenseCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.resources.CreateExpenseResource;

public class CreateExpenseCommandFromResourceAssembler {
    public static CreateExpenseCommand toCommandFromResource(CreateExpenseResource resource) {
        return new CreateExpenseCommand(resource.amount(),
                resource.walletId(),
                resource.categoryId(),
                resource.periodRecurrence());
    }
}
