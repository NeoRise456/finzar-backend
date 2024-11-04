package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.CreateExpenseCommand;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources.CreateExpenseResource;

public class CreateExpenseCommandFromResourceAssembler {
    public static CreateExpenseCommand toCommandFromResource(CreateExpenseResource resource) {
        return new CreateExpenseCommand(resource.amount(),
                resource.walletId(),
                resource.categoryId(),
                resource.periodRecurrenceId());
    }
}
