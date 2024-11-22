package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.entities.Category;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.resources.CategoryResource;

public class CategoryResourceFromEntityAssembler {
    public static CategoryResource toResourceFromEntity(Category entity) {
        return new CategoryResource(entity.getId(), entity.getName(), entity.getDescription());
    }
}
