package task.internaltransfers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDto {
    @JsonProperty("fromAccount")
    private String fromAccount;
    @JsonProperty("fromCurrency")
    private int fromCurrency;
    @JsonProperty("toAccount")
    private String toAccount;
    @JsonProperty("toCurrency")
    private int toCurrency;
    @JsonProperty("transaction")
    private String transaction;
    @JsonProperty("amount")
    private BigDecimal amount;
}
