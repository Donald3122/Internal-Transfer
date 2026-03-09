package task.internaltransfers.config.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "payments", schema = "public")
public class PaymentsEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;
    @Column(name = "amount")
    private BigDecimal amount;
    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "currency_id")
    private Integer currencyId;
    @Column(name = "operator_payment_id")
    private String operatorPaymentId;
    @Column(name = "requisite")
    private String requisite;
    @Column(name = "status")
    private String status;
    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;

}
