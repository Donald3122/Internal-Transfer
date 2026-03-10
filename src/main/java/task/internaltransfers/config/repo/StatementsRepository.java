package task.internaltransfers.config.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import task.internaltransfers.config.entity.TransactionsEntity;
import task.internaltransfers.dto.AccountStatementDto;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatementsRepository extends JpaRepository<TransactionsEntity, Long> {
    @Query(value = "SELECT t.status, t.from_amount, t.debit_currency_id, t.comment, t.balance_after " +
            "FROM get_account_statement(:accountNumber, :fromDate, :toDate) t",
            nativeQuery = true)
    List<AccountStatementDto> getAccountStatement(
            @Param("accountNumber") String accountNumber,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate
    );
}
