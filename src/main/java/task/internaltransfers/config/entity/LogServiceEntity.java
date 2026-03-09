package task.internaltransfers.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "log_service", schema = "public")
public class LogServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "command", nullable = false)
    private String command;

    @Column(name = "request")
    private String request;

    @Column(name = "response")
    private String response;

    @CreationTimestamp
    @Column(name = "log_date_time")
    private LocalDateTime logDateTime;

    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "requisite")
    private String requisite;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "inner_code", nullable = false)
    private String innerCode;

    @Column(name = "inner_message", nullable = false)
    private String innerMessage;

}
