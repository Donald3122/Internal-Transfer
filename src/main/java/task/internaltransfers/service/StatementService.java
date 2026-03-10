package task.internaltransfers.service;

import task.internaltransfers.dto.AccountStatementDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatementService {
    List<AccountStatementDto> getAccountStatement(String accountNumber, LocalDateTime fromDate, LocalDateTime toDate);
}
