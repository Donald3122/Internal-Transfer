package task.internaltransfers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatementDto {
    private String status;
    private BigDecimal amount;
    private Integer currencyId;
    private String comment;
    private BigDecimal balanceAfter;
}
