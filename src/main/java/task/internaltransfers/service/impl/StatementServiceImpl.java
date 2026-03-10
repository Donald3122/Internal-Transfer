package task.internaltransfers.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import task.internaltransfers.config.repo.StatementsRepository;
import task.internaltransfers.dto.AccountStatementDto;
import task.internaltransfers.service.StatementService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatementServiceImpl implements StatementService {
    private final StatementsRepository statementsRepository;
    @Override
    public List<AccountStatementDto> getAccountStatement(String accountNumber, LocalDateTime fromDate, LocalDateTime toDate) {
        return statementsRepository.getAccountStatement(accountNumber, fromDate, toDate);
    }
}
