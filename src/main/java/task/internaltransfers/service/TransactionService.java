package task.internaltransfers.service;

import task.internaltransfers.dto.RequestDto;
import task.internaltransfers.dto.ResponseDto;

/**
 * Сервис создает транзакции и запись в таблице Transactions
 */
public interface TransactionService {
    public ResponseDto execute(RequestDto requestDto,String paymentId);
}
