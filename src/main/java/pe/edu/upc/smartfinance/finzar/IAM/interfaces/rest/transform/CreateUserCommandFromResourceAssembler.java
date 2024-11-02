package pe.edu.upc.smartfinance.finzar.IAM.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.IAM.domain.model.commands.CreateUserCommand;
import pe.edu.upc.smartfinance.finzar.IAM.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.username(),
                resource.email(),
                resource.password());
    }
}
