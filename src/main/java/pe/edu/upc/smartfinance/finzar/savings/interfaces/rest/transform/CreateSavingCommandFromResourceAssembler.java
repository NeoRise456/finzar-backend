package pe.edu.upc.smartfinance.finzar.savings.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.savings.domain.model.commands.CreateSavingCommand;
import pe.edu.upc.smartfinance.finzar.savings.interfaces.rest.resources.CreateSavingResource;

public class CreateSavingCommandFromResourceAssembler {
    public static CreateSavingCommand toCommandFromResource(CreateSavingResource resource) {
        return new CreateSavingCommand(
                resource.userId(),
                resource.name(),
                resource.totalGoal(),
                resource.currentAmount(),
                resource.categoryId(),
                resource.startDate(),
                resource.endDate()
        );
    }
}