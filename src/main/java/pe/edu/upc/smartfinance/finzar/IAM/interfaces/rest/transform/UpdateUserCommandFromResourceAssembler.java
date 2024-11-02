package pe.edu.upc.smartfinance.finzar.IAM.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.IAM.domain.model.commands.UpdateUserCommand;
import pe.edu.upc.smartfinance.finzar.IAM.interfaces.rest.resources.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(Long userId, UpdateUserResource resource) {
        return new UpdateUserCommand(userId, resource.username(), resource.email(), resource.password());
    }
}
