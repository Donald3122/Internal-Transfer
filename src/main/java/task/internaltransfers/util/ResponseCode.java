package task.internaltransfers.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public enum ResponseCode {
    SUCCESS(0,"Успех – ошибок нет"),
    PROCESSING(90,"Проведение платежа не окончено"),
    PENDING(1,"Платёж в обработке"),
    ACCOUNT_NOT_ACTIVE(79,"Счёт абонента неактивен"),
    DUPLICATE(10,"Дублирование платежа"),
    BAD_REQUEST(202,"Ошибка данных запроса"),
    INTERNAL_SERVER_ERROR(500, "Ошибка сервера"),
    INTERNAL_SERVER_ERROR_transaction(500, "Ошибка сервера, транзакция не создана "),
    AMOUNT_TOO_LARGE_MSG(202, "Не достаточно средств на счёте"),
    REQUISITE_NOT_FOUND(79, "Реквизит не найден"),
    BLACKLIST_MATCH(79,"911.Невозможно совершить операцию, попробуйте позже или обратитесь в отделение банка");


    private final int code;
    private final String comment;
    ResponseCode(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }
}
