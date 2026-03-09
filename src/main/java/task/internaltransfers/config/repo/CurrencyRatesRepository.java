package task.internaltransfers.config.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.internaltransfers.config.entity.CurrencyRatesEntity;

import java.util.Optional;
@Repository
public interface CurrencyRatesRepository extends JpaRepository<CurrencyRatesEntity,Long> {
    Optional<CurrencyRatesEntity> findByCurrencyId (Long currencyId);
}
