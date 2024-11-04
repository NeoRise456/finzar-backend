package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.entities.Category;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources.CategoryResource;

public class CategoryResourceFromEntityAssembler {
    public static CategoryResource toResourceFromEntity(Category entity) {
        return new CategoryResource(entity.getId(), entity.getName(), entity.getDescription());
    }
}
