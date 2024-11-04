package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.entities.TransactionType;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources.TransactionTypeResource;

public class TransactionTypeResourceFromEntityAssembler {
    public static TransactionTypeResource toResourceFromEntity(TransactionType entity) {
        return new TransactionTypeResource(entity.getId(), entity.getName());
    }
}
