package pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.smartfinance.finzar.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.entities.TransactionType;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "transactions")
public class Transaction extends AuditableAbstractAggregateRoot<Transaction> {


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;


    @Column(name = "note",columnDefinition = "text")
    private String note;


    @Min(0)
    @NotNull
    @Column(name = "amount")
    private Double amount;

    @NotNull
    @NotBlank
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    public Transaction() {
    }

    public Transaction updateInformation(Double amount, String note, LocalDateTime transactionDate) {
        this.amount = amount;
        this.note = note;
        this.transactionDate = transactionDate;
        return this;
    }


}
