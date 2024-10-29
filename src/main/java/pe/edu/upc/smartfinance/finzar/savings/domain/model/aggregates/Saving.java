package pe.edu.upc.smartfinance.finzar.savings.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.smartfinance.finzar.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "savings")
public class Saving extends AuditableAbstractAggregateRoot<Saving> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Column(name = "user_id", nullable = false)
    private int userId;

    @NotNull
    @NotBlank
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Min(1)
    @Column(name = "total_goal", nullable = false)
    private int totalGoal;

    @Setter
    @Min(0)
    @Column(name = "current_amount", nullable = false)
    private int currentAmount;

    @NotNull
    @Column(name = "category_id", nullable = false)
    private int categoryId;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    public Saving(int userId, String name, int totalGoal, int currentAmount, int categoryId, LocalDate startDate, LocalDate endDate) {
        this.userId = userId;
        this.name = name;
        this.totalGoal = totalGoal;
        this.currentAmount = currentAmount;
        this.categoryId = categoryId;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    protected Saving() {}

    public void updateAmount(int newAmount) {
        this.currentAmount = newAmount;
    }

    public void updateDates(LocalDate newStartDate, LocalDate newEndDate) {
        this.startDate = newStartDate;
        this.endDate = newEndDate;
    }


}
