package task.internaltransfers.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import task.internaltransfers.config.entity.PaymentsEntity;
import task.internaltransfers.config.repo.PaymentRepository;
import task.internaltransfers.dto.RequestDto;
import task.internaltransfers.dto.ResponseDto;
import task.internaltransfers.exception.AccountNotFoundException;
import task.internaltransfers.exception.DuplicatePaymentException;
import task.internaltransfers.service.*;
import task.internaltransfers.util.ResponseCode;

@Service
@AllArgsConstructor
public class PayServiceImpl implements PayService {
    private final AccountValidationService accountValidationService;
    private final TransactionService transactionService;
    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;
    private final BCLogger logger;
    @Override
    @Transactional
    public ResponseDto execute(RequestDto requestDto) {
        ResponseDto responseDto;
        responseDto = paymentService.getExist(requestDto);
        if(responseDto.getResponse().getStatus() != ResponseCode.SUCCESS.getCode()){
            logger.saveLogService("Pay",requestDto.toString(),responseDto.getResponse().toString(), Long.valueOf(requestDto.getTransaction()),requestDto.getFromAccount(),"Failed",String.valueOf(responseDto.getResponse().getStatus()), responseDto.getResponse().getComment());
            throw new DuplicatePaymentException(responseDto.getResponse().getComment());
        }
        PaymentsEntity payments = paymentService.createPayment(requestDto);

        responseDto = accountValidationService.checkAccount(requestDto);
        if(responseDto.getResponse().getStatus() != ResponseCode.SUCCESS.getCode()){
            logger.saveLogService("Pay",requestDto.toString(),responseDto.getResponse().toString(), Long.valueOf(requestDto.getTransaction()),requestDto.getFromAccount(),"Failed",String.valueOf(responseDto.getResponse().getStatus()), responseDto.getResponse().getComment());
            payments.setStatus("Failed");
            paymentRepository.save(payments);
            throw new AccountNotFoundException(responseDto.getResponse().getComment());
        }
        responseDto = transactionService.execute(requestDto,payments.getId());
        if(responseDto.getResponse().getStatus() != ResponseCode.SUCCESS.getCode()){
            logger.saveLogService("Pay",requestDto.toString(),responseDto.getResponse().toString(), Long.valueOf(requestDto.getTransaction()),requestDto.getFromAccount(),"Failed",String.valueOf(responseDto.getResponse().getStatus()), responseDto.getResponse().getComment());
            payments.setStatus("Failed");
            paymentRepository.save(payments);
            throw new AccountNotFoundException(responseDto.getResponse().getComment());
        }
        logger.saveLogService("Pay",requestDto.toString(),responseDto.getResponse().toString(),Long.valueOf(requestDto.getTransaction()),requestDto.getFromAccount(),"Success",String.valueOf(ResponseCode.SUCCESS.getCode()), ResponseCode.SUCCESS.getComment());
        payments.setStatus("Success");
        paymentRepository.save(payments);

        return responseDto;
    }
}
