package pe.edu.upc.smartfinance.finzar.savingsgraphics.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.valueobjects.CurrentAmount;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.valueobjects.GoalAmount;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.valueobjects.SavingPeriod;
import pe.edu.upc.smartfinance.finzar.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "savingsgraphics")
public class SavingsGraphics extends AuditableAbstractAggregateRoot<pe.edu.upc.smartfinance.finzar.savings.domain.model.aggregates.Saving> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "total_goal", nullable = false))
    private GoalAmount totalGoal;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "current_amount", nullable = false))
    private CurrentAmount currentAmount;

    @Getter
    @Embedded
    private SavingPeriod period;

    public SavingsGraphics(GoalAmount totalGoal, CurrentAmount currentAmount, SavingPeriod period) {
        if (totalGoal == null || currentAmount == null || period == null) {
            throw new IllegalArgumentException("All parameters must not be null");
        }
        this.totalGoal = totalGoal;
        this.currentAmount = currentAmount;
        this.period = period;
    }

    public SavingsGraphics() {
    }

}
