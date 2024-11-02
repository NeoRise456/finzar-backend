package pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands.CreateCategoryCommand;
import pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.resources.CreateCategoryResource;

public class CreateCategoryCommandFromResourceAssembler {
    public static CreateCategoryCommand toCommandFromResource(CreateCategoryResource resource) {
        return new CreateCategoryCommand(resource.name(), resource.description());
    }
}
