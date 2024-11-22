package pe.edu.upc.smartfinance.finzar.cashflow.domain.services;

import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.CreateCategoryCommand;

public interface CategoryCommandService {
    Long handle(CreateCategoryCommand command);
}
