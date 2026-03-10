package task.internaltransfers.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task.internaltransfers.config.repo.StatementsRepository;
import task.internaltransfers.dto.AccountStatementDto;
import task.internaltransfers.service.StatementService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatementServiceImpl implements StatementService {
    private final StatementsRepository statementsRepository;
    @Override
    public Page<AccountStatementDto> getAccountStatement(String accountNumber, LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        int offset = (int) pageable.getOffset();
        int limit = pageable.getPageSize();

        List<Object[]> results = statementsRepository.getAccountStatement(accountNumber, fromDate, toDate, offset, limit);

        List<AccountStatementDto> dtos = results.stream()
                .map(row -> new AccountStatementDto(
                        (String) row[0], // status
                        row[1] != null ? new BigDecimal(row[1].toString()) : null, // amount
                        row[2] != null ? ((Number) row[2]).intValue() : null, // currencyId
                        (String) row[3], // comment
                        row[4] != null ? new BigDecimal(row[4].toString()) : null // balanceAfter
                ))
                .collect(Collectors.toList());

        // Так как функция возвращает только нужную страницу, totalElements можно передавать равным dtos.size()
        return new PageImpl<>(dtos, pageable, dtos.size());
    }
}
