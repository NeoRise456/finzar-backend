package pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.IAM.domain.model.aggregates.User;
import pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.resources.SimplifiedUserResource;

public class SimplifiedUserResourceFromEntityAssembler {
    public static SimplifiedUserResource toResourceFromEntity(User entity) {
        return new SimplifiedUserResource(entity.getId(), entity.getUsername());
    }
}
