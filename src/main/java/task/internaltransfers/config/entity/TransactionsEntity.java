package task.internaltransfers.config.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transactions", schema = "public")
public class TransactionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "from_amount")
    private BigDecimal from_amount;
    @Column(name = "to_amount")
    private BigDecimal to_amount;
    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "credit")
    private String credit;
    @Column(name = "debit_currency_id")
    private Integer debitCurrencyId;
    @Column(name = "debit")
    private String debit;
    @Column(name = "status")
    private String status;
    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;
    @Column(name = "payment_id")
    private String paymentId;
    @Column(name = "credit_currency_id")
    private Integer creditCurrencyId;
    @Column(name = "comment")
    private String comment;

}
