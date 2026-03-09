package task.internaltransfers.service;

import java.math.BigDecimal;
/**
 * Универсальный метод конвертации любой валюты в любую
 * @return сумма в валюте получателя
 */
public interface CurrencyConversionService {
    public BigDecimal convert(BigDecimal amount, Long fromCurrencyId, Long toCurrencyId);
}
