package task.internaltransfers.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import task.internaltransfers.dto.AccountStatementDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface StatementService {
    Page<AccountStatementDto> getAccountStatement(String accountNumber, LocalDate fromDate, LocalDate toDate, Pageable pageable);
}
