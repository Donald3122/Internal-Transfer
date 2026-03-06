package task.internaltransfers.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts", schema = "public")
public class AccountsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "currency_id")
    private long currencyId;
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "customer_id")
    private Integer customerId;

}
