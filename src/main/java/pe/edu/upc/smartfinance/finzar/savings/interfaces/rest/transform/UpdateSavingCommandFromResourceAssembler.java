package pe.edu.upc.smartfinance.finzar.savings.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.savings.domain.model.commands.UpdateSavingCommand;
import pe.edu.upc.smartfinance.finzar.savings.interfaces.rest.resources.SavingResource;

public class UpdateSavingCommandFromResourceAssembler {
    public static UpdateSavingCommand toCommandFromResource(Long savingId, SavingResource resource) {
        return new UpdateSavingCommand(
                savingId,
                resource.name(),
                resource.totalGoal(),
                resource.currentAmount(),
                resource.categoryId(),
                resource.startDate(),
                resource.endDate()
        );
    }
}