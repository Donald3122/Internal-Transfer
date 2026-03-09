package task.internaltransfers.service;

import task.internaltransfers.dto.RequestDto;
import task.internaltransfers.dto.ResponseDto;

/**
 * Метод для проведения плптежа(перевода) с одного счёта на другой
 * Мы получаем в
 */
public interface PayService {
    public ResponseDto execute(RequestDto requestDto);
}
