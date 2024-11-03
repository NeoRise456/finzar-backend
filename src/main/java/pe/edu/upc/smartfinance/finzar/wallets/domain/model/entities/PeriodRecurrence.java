package pe.edu.upc.smartfinance.finzar.wallets.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.valueobjects.PeriodRecurrences;

@Entity
@Table(name = "period_recurrences")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class PeriodRecurrence {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 22)
    private PeriodRecurrences name;

    public PeriodRecurrence(PeriodRecurrences name) {
        this.name = name;
    }

    public String getStringName() {
        return name.name();
    }

    public static PeriodRecurrence toPeriodRecurrenceFromName(String name) {
        return new PeriodRecurrence(PeriodRecurrences.valueOf(name));
    }


}
