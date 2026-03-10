package task.internaltransfers.config.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import task.internaltransfers.config.entity.TransactionsEntity;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface StatementsRepository extends JpaRepository<TransactionsEntity, Long> {

    @Query(value = "SELECT * FROM get_account_statement(:accountNumber, :fromDate, :toDate, :offset, :limit)", nativeQuery = true)
    List<Object[]> getAccountStatement(
            @Param("accountNumber") String accountNumber,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("offset") int offset,
            @Param("limit") int limit
    );
}
