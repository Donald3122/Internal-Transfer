package task.internaltransfers.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import task.internaltransfers.config.entity.PaymentsEntity;
import task.internaltransfers.config.repo.PaymentRepository;
import task.internaltransfers.dto.RequestDto;
import task.internaltransfers.dto.ResponseDto;
import task.internaltransfers.service.PaymentService;
import task.internaltransfers.util.ResponseCode;
import task.internaltransfers.util.ResponseUtil;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    @Override
    public PaymentsEntity createPayment(RequestDto requestDto) {

        PaymentsEntity payments = PaymentsEntity.builder()
                .requisite(requestDto.getFromAccount())
                .operatorPaymentId(requestDto.getTransaction())
                .amount(requestDto.getAmount())
                .status("Pending")
                .currencyId(requestDto.getFromCurrency())
                .build();
        return paymentRepository.save(payments);
    }

    @Override
    public ResponseDto getExist(RequestDto requestDto) {
        PaymentsEntity payments = paymentRepository.findByOperatorPaymentId(requestDto.getTransaction());
        if (payments != null) {
            return ResponseUtil.builderCustomResponse(ResponseCode.DUPLICATE.getCode(), ResponseCode.DUPLICATE.getComment());
        }
        return ResponseUtil.builderCustomResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getComment());
    }
}
