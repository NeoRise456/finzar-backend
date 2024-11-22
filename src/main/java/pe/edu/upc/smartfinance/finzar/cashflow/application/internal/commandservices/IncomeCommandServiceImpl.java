package pe.edu.upc.smartfinance.finzar.cashflow.application.internal.commandservices;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.cashflow.application.internal.outboundservices.ExternalTransactionService;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.aggregates.Income;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.AddTransactionByIncomeIdCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.CreateIncomeCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.DeleteIncomeCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.valueobjects.PeriodRecurrences;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.IncomeCommandService;
import pe.edu.upc.smartfinance.finzar.cashflow.infrastructure.persistence.jpa.repositories.CategoryRepository;
import pe.edu.upc.smartfinance.finzar.cashflow.infrastructure.persistence.jpa.repositories.IncomeRepository;
import pe.edu.upc.smartfinance.finzar.cashflow.infrastructure.persistence.jpa.repositories.PeriodRecurrenceRepository;
import pe.edu.upc.smartfinance.finzar.wallets.infrastructure.persistence.jpa.repositories.WalletRepository;

@Service
public class IncomeCommandServiceImpl implements IncomeCommandService {

    private final IncomeRepository incomeRepository;

    //TODO: Add Wallet External Service
    private final WalletRepository walletRepository;
    private final CategoryRepository categoryRepository;
    private final PeriodRecurrenceRepository periodRecurrenceRepository;

    private final ExternalTransactionService externalTransactionService;

    public IncomeCommandServiceImpl(IncomeRepository incomeRepository ,
                                    WalletRepository walletRepository,
                                    CategoryRepository categoryRepository,
                                    PeriodRecurrenceRepository periodRecurrenceRepository,
                                    @Lazy ExternalTransactionService externalTransactionService) {
        this.incomeRepository = incomeRepository;
        this.walletRepository = walletRepository;
        this.categoryRepository = categoryRepository;
        this.periodRecurrenceRepository = periodRecurrenceRepository;
        this.externalTransactionService = externalTransactionService;
    }

    @Override
    public Long handle(CreateIncomeCommand command) {

        var wallet = this.walletRepository.findById(command.walletId());

        if(!wallet.isPresent()){
            throw new IllegalArgumentException("Wallet not found");
        }

        var category = this.categoryRepository.findById(command.categoryId());

        if(!category.isPresent()){
            throw new IllegalArgumentException("Category not found");
        }

        var periodRecurrence = this.periodRecurrenceRepository.findByName(
                PeriodRecurrences.valueOf(command.periodRecurrence())
        );

        if(!periodRecurrence.isPresent()){
            throw new IllegalArgumentException("Period Recurrence not found");
        }

        var earning = new Income(
                command.amount(),
                wallet.get(),
                category.get(),
                periodRecurrence.get()
        );

        this.incomeRepository.save(earning);

        return earning.getId();
    }

    @Override
    public Boolean handle(DeleteIncomeCommand command) {
        if(!this.incomeRepository.existsById(command.earningId())){
            throw new IllegalArgumentException("Income not found");
        }
        this.incomeRepository.deleteById(command.earningId());
        return !this.incomeRepository.existsById(command.earningId());
    }

    @Override
    public void handle(AddTransactionByIncomeIdCommand command) {

        if (!this.incomeRepository.existsById(command.incomeId())) {
            throw new IllegalArgumentException("Income not found");
        }

        var income = this.incomeRepository.findById(command.incomeId()).get();

        var transaction = externalTransactionService.fetchTransactionById(command.transactionId());

        income.getTransactions().add(transaction.get());

        this.incomeRepository.save(income);
    }


}
