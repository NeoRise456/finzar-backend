package pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.smartfinance.finzar.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.entities.Category;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.entities.PeriodRecurrence;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;

@Entity
@Table(name = "expenses")
@Getter
public class Expense extends AuditableAbstractAggregateRoot<Expense> {

    @Min(0)
    @NotNull
    @Column(name = "amount")
    private Double amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "period_recurrence_id")
    private PeriodRecurrence periodRecurrence;

    public Expense() {
    }

    public Expense updateInformation(Double amount, Category category, PeriodRecurrence periodRecurrence) {
        this.amount = amount;
        this.category = category;
        this.periodRecurrence = periodRecurrence;
        return this;
    }
}
