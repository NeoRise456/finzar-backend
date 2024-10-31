package pe.edu.upc.smartfinance.finzar.savings.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.commands.CreateSavingCommand;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.valueobjects.CurrentAmount;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.valueobjects.GoalAmount;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.valueobjects.SavingPeriod;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.valueobjects.UserId;
import pe.edu.upc.smartfinance.finzar.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDate;

@Entity
@Table(name = "savings")
public class Saving extends AuditableAbstractAggregateRoot<Saving> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "user_id", nullable = false))
    private UserId userId;

    @Getter
    @NotBlank
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "total_goal", nullable = false))
    private GoalAmount totalGoal;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "current_amount", nullable = false))
    private CurrentAmount currentAmount;

    @Getter
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Getter
    @Embedded
    private SavingPeriod period;


    // Constructor con parámetros
    public Saving(UserId userId, String name, GoalAmount totalGoal,
                  CurrentAmount currentAmount, Long categoryId,
                  SavingPeriod period) {
        this.userId = userId;
        this.name = name;
        this.totalGoal = totalGoal;
        this.currentAmount = currentAmount;
        this.categoryId = categoryId;
        this.period = period;
    }

    // Constructor sin parámetros para JPA
    public Saving() {
    }


    // Constructor basado en CreateSavingCommand
    public Saving(CreateSavingCommand command) {
        this.userId = new UserId(command.userId());
        this.name = command.name();
        this.totalGoal = new GoalAmount(command.totalGoal());
        this.currentAmount = new CurrentAmount(command.currentAmount());
        this.categoryId = command.categoryId();
        this.period = new SavingPeriod(command.startDate(), command.endDate());
    }

    public Saving updateInformation(String name, double totalGoal,
                                    double currentAmount, Long categoryId,
                                    LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.totalGoal = new GoalAmount(totalGoal);
        this.currentAmount = new CurrentAmount(currentAmount);
        this.categoryId = categoryId;
        this.period = new SavingPeriod(startDate, endDate);
        return this;
    }

    public void addAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to add must be positive");
        }
        this.currentAmount = new CurrentAmount(this.currentAmount.value() + amount);
    }

}


