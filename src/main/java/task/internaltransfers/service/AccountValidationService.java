package task.internaltransfers.service;

import org.hibernate.boot.BootLogging_$logger;
import task.internaltransfers.dto.RequestDto;
import task.internaltransfers.dto.ResponseDto;

/**
 * Сервис проверяет счет на сушествование, проверяет статус счёта, баланс счёта и не является ли счёт отправки и поступления одним и темже
 */
public interface AccountValidationService {
    public ResponseDto checkAccount(RequestDto requestDto);
}
