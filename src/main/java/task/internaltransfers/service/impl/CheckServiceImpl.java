package task.internaltransfers.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import task.internaltransfers.config.entity.AccountsEntity;
import task.internaltransfers.config.repo.AccountRepository;
import task.internaltransfers.dto.AccountDto;
import task.internaltransfers.dto.ResponseDto;
import task.internaltransfers.mapper.AccountMapper;
import task.internaltransfers.service.CheckService;
import task.internaltransfers.util.ResponseCode;
import task.internaltransfers.util.ResponseUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {
    private final AccountRepository account;
    private final AccountMapper mapper;
    @Override
    public ResponseDto execute(int customerId) {
        try{
            List<AccountsEntity> accounts = account.findByCustomerId(customerId);
            if(accounts.isEmpty()){
                return ResponseUtil.builderCustomResponse(ResponseCode.REQUISITE_NOT_FOUND.getCode(),ResponseCode.REQUISITE_NOT_FOUND.getComment());
            }
            List<AccountDto> accountDto = accounts.stream()
                    .map(mapper::mapToDto)
                    .toList();



            return ResponseUtil.builderResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getComment(),"Accounts found",accountDto);
        }catch (Exception e){
            return null;
        }
    }
}
