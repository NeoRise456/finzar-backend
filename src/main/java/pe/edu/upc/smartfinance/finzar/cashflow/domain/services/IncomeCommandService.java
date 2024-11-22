package pe.edu.upc.smartfinance.finzar.cashflow.domain.services;


import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.AddTransactionByIncomeIdCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.CreateIncomeCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.DeleteIncomeCommand;



public interface IncomeCommandService {
    Long handle(CreateIncomeCommand command);
    Boolean handle(DeleteIncomeCommand command);
    void handle(AddTransactionByIncomeIdCommand command);

}
