package task.internaltransfers.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import task.internaltransfers.config.entity.AccountsEntity;
import task.internaltransfers.config.repo.AccountRepository;
import task.internaltransfers.dto.AccountDto;
import task.internaltransfers.dto.ResponseDto;
import task.internaltransfers.mapper.AccountMapper;
import task.internaltransfers.service.BCLogger;
import task.internaltransfers.service.CheckService;
import task.internaltransfers.util.ResponseCode;
import task.internaltransfers.util.ResponseUtil;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {
    private final AccountRepository accountRepository;
    private final AccountMapper mapper;
    private final BCLogger logger;
    @Override
    public ResponseDto execute(String customerId) {
        log.info("Что внутри customersId = {}",customerId);
        try{
            List<AccountsEntity> accounts = accountRepository.findByCustomerId(customerId);
            if(accounts.isEmpty()){
                logger.saveLogService("Check",customerId,"null",null,customerId,"Failed",String.valueOf(ResponseCode.REQUISITE_NOT_FOUND.getCode()), ResponseCode.REQUISITE_NOT_FOUND.getComment());
                return ResponseUtil.builderCustomResponse(ResponseCode.REQUISITE_NOT_FOUND.getCode(),ResponseCode.REQUISITE_NOT_FOUND.getComment());
            }
            List<AccountDto> accountDto = accounts.stream()
                    .map(mapper::mapToDto)
                    .toList();

            ResponseDto responseDto = ResponseUtil.builderResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getComment(),"Accounts found",accountDto);
            logger.saveLogService("Check",customerId,responseDto.getResponse().toString(),null,customerId,"Success",String.valueOf(ResponseCode.SUCCESS.getCode()), ResponseCode.SUCCESS.getComment());

            log.info("Что внутри customersId = {}",responseDto.toString());
            return responseDto;
        }catch (Exception e){
            logger.saveLogService("Check",customerId,"null",null,customerId,"Failed",String.valueOf(ResponseCode.INTERNAL_SERVER_ERROR.getCode()), ResponseCode.INTERNAL_SERVER_ERROR.getComment());

            return ResponseUtil.builderCustomResponse(ResponseCode.INTERNAL_SERVER_ERROR.getCode(), ResponseCode.INTERNAL_SERVER_ERROR.getComment());
        }
    }
}
