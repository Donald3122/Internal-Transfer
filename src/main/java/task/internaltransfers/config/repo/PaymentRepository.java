package task.internaltransfers.config.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.internaltransfers.config.entity.LogServiceEntity;
import task.internaltransfers.config.entity.PaymentsEntity;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentsEntity,Long>{
    PaymentsEntity findByOperatorPaymentId (String operatorPaymentId);
}
