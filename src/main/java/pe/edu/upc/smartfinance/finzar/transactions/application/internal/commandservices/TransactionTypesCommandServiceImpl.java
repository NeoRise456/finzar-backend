package pe.edu.upc.smartfinance.finzar.transactions.application.internal.commandservices;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.SeedTransactionTypesCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.entities.TransactionType;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.valueobjects.TransactionTypes;
import pe.edu.upc.smartfinance.finzar.transactions.domain.services.TransactionTypesCommandService;
import pe.edu.upc.smartfinance.finzar.transactions.infrastructure.persistence.jpa.repositories.TransactionTypeRepository;

import java.util.Arrays;

public class TransactionTypesCommandServiceImpl implements TransactionTypesCommandService {

    private final TransactionTypeRepository transactionTypeRepository;

    public TransactionTypesCommandServiceImpl(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public void handle(SeedTransactionTypesCommand command) {
        Arrays.stream(TransactionTypes.values())
                .forEach(transactionType -> {
                    if (!transactionTypeRepository.existsByName(transactionType)){
                        transactionTypeRepository.save(
                                new TransactionType(
                                        TransactionTypes.valueOf(transactionType.name())
                                )
                        );
                    }

                });
    }
}
