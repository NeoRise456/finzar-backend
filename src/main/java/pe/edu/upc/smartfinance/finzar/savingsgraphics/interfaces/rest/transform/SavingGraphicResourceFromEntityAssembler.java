package pe.edu.upc.smartfinance.finzar.savingsgraphics.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.savingsgraphics.domain.model.aggregates.SavingsGraphics;
import pe.edu.upc.smartfinance.finzar.savingsgraphics.interfaces.rest.resources.SavingGraphicResource;

public class SavingGraphicResourceFromEntityAssembler {
    public static SavingGraphicResource toResourceFromEntity(SavingsGraphics entity){
        return new SavingGraphicResource(
                Math.toIntExact(entity.getId()),
                entity.getTotalGoal().value(),
                entity.getCurrentAmount().value(),
                entity.getPeriod().startDate(),
                entity.getPeriod().endDate()
        );
    }
}
