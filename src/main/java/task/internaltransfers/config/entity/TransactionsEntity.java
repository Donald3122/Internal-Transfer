package task.internaltransfers.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions", schema = "public")
public class TransactionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "created",insertable = false, updatable = false)
    private LocalDateTime created;
    @Column(name = "credit")
    private String credit;
    @Column(name = "debit_currency_id")
    private Integer debitCurrencyId;
    @Column(name = "debit")
    private String debit;
    @Column(name = "integration_id")
    private Long integrationId;
    @Column(name = "position")
    private Long position;
    @Column(name = "status")
    private String status;
    @Column(name = "type")
    private String type;
    @Column(name = "updated",insertable = false, updatable = false)
    private LocalDateTime updated;
    @Column(name = "payment_id")
    private String paymentId;
    @Column(name = "credit_currency_id")
    private Integer creditCurrencyId;
    @Column(name = "comment")
    private String comment;

}
