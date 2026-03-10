package task.internaltransfers.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import task.internaltransfers.config.entity.AccountsEntity;
import task.internaltransfers.config.entity.TransactionsEntity;
import task.internaltransfers.config.repo.AccountRepository;
import task.internaltransfers.config.repo.TransactionsRepository;
import task.internaltransfers.dto.RequestDto;
import task.internaltransfers.dto.ResponseDto;
import task.internaltransfers.service.CurrencyConversionService;
import task.internaltransfers.service.TransactionService;
import task.internaltransfers.util.ResponseCode;
import task.internaltransfers.util.ResponseUtil;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final CurrencyConversionService convert;
    private final AccountRepository accountRepository;
    private final TransactionsRepository transactionsRepository;
    @Override
    public ResponseDto execute(RequestDto requestDto,String paymentId) {

        AccountsEntity fromAccounts = accountRepository.findByAccountNumber(requestDto.getFromAccount());
        AccountsEntity toAccounts = accountRepository.findByAccountNumber(requestDto.getToAccount());

        BigDecimal amount = convert.convert(requestDto.getAmount(), fromAccounts.getCurrencyId(), toAccounts.getCurrencyId());


        fromAccounts.setBalance(fromAccounts.getBalance().subtract(requestDto.getAmount()));
        toAccounts.setBalance(toAccounts.getBalance().add(amount));

        accountRepository.save(fromAccounts);
        accountRepository.save(toAccounts);


        TransactionsEntity transactions = TransactionsEntity.builder()
                .comment("Перевод денежных средств в размере "+ requestDto.getAmount()+ " с счёта "+requestDto.getFromAccount()+ "на счёт "+ requestDto.getToAccount())
                .debit(requestDto.getFromAccount())
                .credit(requestDto.getToAccount())
                .creditCurrencyId(Math.toIntExact(requestDto.getToCurrency()))
                .debitCurrencyId(Math.toIntExact(requestDto.getFromCurrency()))
                .fromAmount(requestDto.getAmount())
                .toAmount(amount)
                .balanceAfter(fromAccounts.getBalance())
                .paymentId(paymentId)
                .status("Success")
                .build();

        transactionsRepository.save(transactions);

        return ResponseUtil.builderCustomResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getComment());
    }
}
