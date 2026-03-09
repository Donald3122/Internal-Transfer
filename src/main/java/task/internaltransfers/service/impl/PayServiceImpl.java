package task.internaltransfers.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import task.internaltransfers.config.entity.PaymentsEntity;
import task.internaltransfers.config.repo.PaymentRepository;
import task.internaltransfers.dto.RequestDto;
import task.internaltransfers.dto.ResponseDto;
import task.internaltransfers.service.AccountValidationService;
import task.internaltransfers.service.PayService;
import task.internaltransfers.service.PaymentService;
import task.internaltransfers.service.TransactionService;
import task.internaltransfers.util.ResponseCode;

@Service
@AllArgsConstructor
public class PayServiceImpl implements PayService {
    private final AccountValidationService accountValidationService;
    private final TransactionService transactionService;
    private final PaymentService paymentService;
    @Override
    @Transactional
    public ResponseDto execute(RequestDto requestDto) {
        ResponseDto responseDto;
        responseDto = paymentService.getExist(requestDto);
        if(responseDto.getResponse().getStatus() != ResponseCode.SUCCESS.getCode()){
            throw new RuntimeException(responseDto.getResponse().getComment());
        }
        PaymentsEntity payments = paymentService.createPayment(requestDto);

        responseDto = accountValidationService.checkAccount(requestDto);
        if(responseDto.getResponse().getStatus() != ResponseCode.SUCCESS.getCode()){
            throw new RuntimeException(responseDto.getResponse().getComment());
        }
        responseDto = transactionService.execute(requestDto,payments.getId());


        return responseDto;
    }
}
