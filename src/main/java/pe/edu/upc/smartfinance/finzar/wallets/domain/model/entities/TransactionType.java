package pe.edu.upc.smartfinance.finzar.wallets.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.valueobjects.PeriodRecurrences;

@Entity
@Table(name = "transaction_types")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 22)
    private PeriodRecurrences name;


    public TransactionType(PeriodRecurrences name) {
        this.name = name;
    }

    public String getStringName() {
        return name.name();
    }

    public static TransactionType toTransactionTypeFromName(String name) {
        return new TransactionType(PeriodRecurrences.valueOf(name));
    }

}
