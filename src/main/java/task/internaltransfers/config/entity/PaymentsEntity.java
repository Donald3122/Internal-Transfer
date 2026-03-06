package task.internaltransfers.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments", schema = "public")
public class PaymentsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "created")
    private Timestamp created;
    @Column(name = "currency_id")
    private Integer currencyId;
    @Column(name = "operator_payment_id")
    private String operatorPaymentId;
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "requisite")
    private String requisite;
    @Column(name = "status")
    private String status;
    @Column(name = "updated")
    private Timestamp updated;
    @Column(name = "operation_date")
    private Date operationDate;

}
