package pe.edu.upc.smartfinance.finzar.IAM.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.IAM.domain.model.aggregates.User;
import pe.edu.upc.smartfinance.finzar.IAM.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(entity.getId(), entity.getUsername(), entity.getEmail(),
                entity.getPassword());
    }
}
