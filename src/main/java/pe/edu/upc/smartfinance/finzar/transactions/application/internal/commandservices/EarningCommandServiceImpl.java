package pe.edu.upc.smartfinance.finzar.transactions.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Earning;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Expense;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.CreateEarningCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.DeleteEarningCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.UpdateEarningCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.entities.PeriodRecurrence;
import pe.edu.upc.smartfinance.finzar.transactions.domain.services.EarningCommandService;
import pe.edu.upc.smartfinance.finzar.transactions.infrastructure.persistence.jpa.repositories.CategoryRepository;
import pe.edu.upc.smartfinance.finzar.transactions.infrastructure.persistence.jpa.repositories.EarningRepository;
import pe.edu.upc.smartfinance.finzar.transactions.infrastructure.persistence.jpa.repositories.PeriodRecurrenceRepository;
import pe.edu.upc.smartfinance.finzar.wallets.infrastructure.persistence.jpa.repositories.WalletRepository;

import java.util.Optional;

@Service
public class EarningCommandServiceImpl implements EarningCommandService {

    private final EarningRepository earningRepository;
    private final WalletRepository walletRepository;
    private final CategoryRepository categoryRepository;
    private final PeriodRecurrenceRepository periodRecurrenceRepository;

    public EarningCommandServiceImpl(EarningRepository earningRepository , WalletRepository walletRepository, CategoryRepository categoryRepository,
                                     PeriodRecurrenceRepository periodRecurrenceRepository) {
        this.earningRepository = earningRepository;
        this.walletRepository = walletRepository;
        this.categoryRepository = categoryRepository;
        this.periodRecurrenceRepository = periodRecurrenceRepository;
    }

    @Override
    public Long handle(CreateEarningCommand command) {

        var wallet = this.walletRepository.findById(command.walletId());

        if(!wallet.isPresent()){
            throw new IllegalArgumentException("Wallet not found");
        }

        var category = this.categoryRepository.findById(command.categoryId());

        if(!category.isPresent()){
            throw new IllegalArgumentException("Category not found");
        }

        var periodRecurrence = this.periodRecurrenceRepository.findById(command.periodRecurrenceId());

        if(!periodRecurrence.isPresent()){
            throw new IllegalArgumentException("Period Recurrence not found");
        }

        var earning = new Earning(
                command.amount(),
                wallet.get(),
                category.get(),
                periodRecurrence.get()
        );

        this.earningRepository.save(earning);

        return earning.getId();
    }

    @Override
    public Boolean handle(DeleteEarningCommand command) {
        if(!this.earningRepository.existsById(command.earningId())){
            throw new IllegalArgumentException("Earning not found");
        }
        this.earningRepository.deleteById(command.earningId());
        return !this.earningRepository.existsById(command.earningId());
    }


    //TODO: Implement update handle for Earning
    @Override
    public Optional<Earning> handle(UpdateEarningCommand command) {
        return Optional.empty();
    }
}
