package pe.edu.upc.smartfinance.finzar.savings.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.savings.domain.model.aggregates.Saving;
import pe.edu.upc.smartfinance.finzar.savings.interfaces.rest.resources.SavingResource;

public class SavingResourceFromEntityAssembler {
    public static SavingResource toResourceFromEntity(Saving entity) {
        return new SavingResource(
                entity.getId(),
                entity.getUserId().value(),
                entity.getName(),
                entity.getTotalGoal().value(),
                entity.getCurrentAmount().value(),
                entity.getCategoryId(),
                entity.getPeriod().startDate(),
                entity.getPeriod().endDate()
        );
    }
}