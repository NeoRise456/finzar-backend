package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.CreateIncomeTransactionCommand;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources.CreateCashflowTransactionResource;

public class CreateIncomeTransactionCommandFromResourceAssembler {
    public static CreateIncomeTransactionCommand toCommandFromResource(CreateCashflowTransactionResource resource, Long incomeId){
        return new CreateIncomeTransactionCommand(
                resource.sourceWalletId(),
                resource.note(),
                resource.amount(),
                resource.transactionDate(),
                incomeId
        );
    }
}
