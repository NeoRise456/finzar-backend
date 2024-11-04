package pe.edu.upc.smartfinance.finzar.transactions.domain.services;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.CreateCategoryCommand;

public interface CategoryCommandService {
    Long handle(CreateCategoryCommand command);
}
