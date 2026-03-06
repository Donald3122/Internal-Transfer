package task.internaltransfers.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "log_service", schema = "public")
public class LogServiceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "command")
    private String command;
    @Column(name = "request_response")
    private String requestResponse;
    @Column(name = "log_date_time")
    private Timestamp logDateTime;
    @Column(name = "ip")
    private String ip;
    @Column(name = "transaction_id")
    private Long transactionId;
    @Column(name = "requisite")
    private String requisite;
    @Column(name = "status")
    private String status;
    @Column(name = "inner_code")
    private String innerCode;
    @Column(name = "inner_message")
    private String innerMessage;
    @Column(name = "provider_code")
    private String providerCode;
    @Column(name = "provider_message")
    private String providerMessage;

}
