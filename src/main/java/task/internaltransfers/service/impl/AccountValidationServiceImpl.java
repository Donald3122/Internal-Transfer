package task.internaltransfers.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import task.internaltransfers.config.entity.AccountsEntity;
import task.internaltransfers.config.repo.AccountRepository;
import task.internaltransfers.dto.RequestDto;
import task.internaltransfers.dto.ResponseDto;
import task.internaltransfers.service.AccountValidationService;
import task.internaltransfers.util.ResponseCode;
import task.internaltransfers.util.ResponseUtil;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AccountValidationServiceImpl implements AccountValidationService {
    private final AccountRepository repository;
    @Override
    public ResponseDto checkAccount(RequestDto requestDto) {
        AccountsEntity toAccounts = repository.findByAccountNumber(requestDto.getToAccount());
        AccountsEntity fromAccounts = repository.findByAccountNumber(requestDto.getFromAccount());

        if (toAccounts == null || fromAccounts == null) {
            // если счет не найден — считаем неактивным
            return ResponseUtil.builderCustomResponse(ResponseCode.REQUISITE_NOT_FOUND.getCode(),"Счёт не найден!");
        }
        if (Objects.equals(requestDto.getToAccount(), requestDto.getFromAccount())){
            return ResponseUtil.builderCustomResponse(ResponseCode.REQUISITE_NOT_FOUND.getCode(),"Счёт отправителя не может совподать со счётом получателя!");
        }
        if (!(fromAccounts.getBalance().compareTo(requestDto.getAmount()) >= 0)){
            return  ResponseUtil.builderCustomResponse(ResponseCode.AMOUNT_TOO_LARGE_MSG.getCode(),ResponseCode.AMOUNT_TOO_LARGE_MSG.getComment());
        }

        // проверяем статус
        boolean toActive = "ACTIVE".equals(toAccounts.getStatus());
        boolean fromActive = "ACTIVE".equals(fromAccounts.getStatus());

        if (!toActive ) {
            return ResponseUtil.builderCustomResponse(
                    ResponseCode.BAD_REQUEST.getCode(),
                    "Счёт получателя неактивен!"
            );
        }
        if (!fromActive ) {
            return ResponseUtil.builderCustomResponse(
                    ResponseCode.BAD_REQUEST.getCode(),
                    "Счёт отправителя неактивен!"
            );
        }
        return ResponseUtil.builderCustomResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getComment());
    }

}
