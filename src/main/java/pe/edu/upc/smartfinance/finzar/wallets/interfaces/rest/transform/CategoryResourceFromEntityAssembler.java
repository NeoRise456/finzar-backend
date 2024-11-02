package pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.wallets.domain.model.entities.Category;
import pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.resources.CategoryResource;

public class CategoryResourceFromEntityAssembler {
    public static CategoryResource toResourceFromEntity(Category entity) {
        return new CategoryResource(entity.getId(), entity.getName(), entity.getDescription());
    }
}
