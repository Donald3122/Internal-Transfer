package task.internaltransfers.service;

import task.internaltransfers.config.entity.PaymentsEntity;
import task.internaltransfers.dto.RequestDto;
import task.internaltransfers.dto.ResponseDto;

public interface PaymentService {
    public PaymentsEntity createPayment(RequestDto requestDto);
    public ResponseDto getExist(RequestDto requestDto);
}
