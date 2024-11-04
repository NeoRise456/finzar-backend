package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.CreateCategoryCommand;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources.CreateCategoryResource;

public class CreateCategoryCommandFromResourceAssembler {
    public static CreateCategoryCommand toCommandFromResource(CreateCategoryResource resource) {
        return new CreateCategoryCommand(resource.name(), resource.description());
    }
}
