package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.CreateCategoryCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.resources.CreateCategoryResource;

public class CreateCategoryCommandFromResourceAssembler {
    public static CreateCategoryCommand toCommandFromResource(CreateCategoryResource resource) {
        return new CreateCategoryCommand(resource.name(), resource.description());
    }
}
