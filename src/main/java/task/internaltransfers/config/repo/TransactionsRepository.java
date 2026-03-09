package task.internaltransfers.config.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import task.internaltransfers.config.entity.PaymentsEntity;
import task.internaltransfers.config.entity.TransactionsEntity;

public interface TransactionsRepository extends JpaRepository<TransactionsEntity,Long> {
    TransactionsEntity findByPaymentId (String paymentId);

}
