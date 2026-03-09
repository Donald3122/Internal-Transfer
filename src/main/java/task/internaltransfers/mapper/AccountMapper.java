package task.internaltransfers.mapper;

import lombok.Setter;
import org.springframework.stereotype.Component;
import task.internaltransfers.config.entity.AccountsEntity;
import task.internaltransfers.dto.AccountDto;

@Setter
@Component
public class AccountMapper {
    public AccountDto mapToDto(AccountsEntity entity){
        AccountDto dto = new AccountDto();
        dto.setBalance(entity.getBalance());
        dto.setCurrency(entity.getCurrencyId());
        dto.setNumber(entity.getAccountNumber());
        return dto;
    }
}
