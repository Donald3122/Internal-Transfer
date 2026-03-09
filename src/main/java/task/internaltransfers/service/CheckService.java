package task.internaltransfers.service;

import task.internaltransfers.dto.ResponseDto;
/**
 *  В метод CheckService мы передаём CustomersId что бы получить все счита клиента для проведения перевода (Pay)
 */
public interface CheckService {
    public ResponseDto execute(int customerId);
}
