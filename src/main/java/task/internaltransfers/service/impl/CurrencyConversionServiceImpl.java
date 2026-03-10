package task.internaltransfers.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import task.internaltransfers.config.repo.CurrencyRatesRepository;
import task.internaltransfers.service.CurrencyConversionService;

import java.awt.image.TileObserver;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class CurrencyConversionServiceImpl implements CurrencyConversionService {
    private final CurrencyRatesRepository currencyRatesRepository;
    @Override
    public BigDecimal convert(BigDecimal amount, Long fromCurrencyId, Long toCurrencyId) {
        log.info("Курс 1 = {}, Курс 2 = {}",fromCurrencyId, toCurrencyId);
        if (Objects.equals(fromCurrencyId, toCurrencyId)) return amount;
        BigDecimal rateFrom = currencyRatesRepository.findByCurrencyId(fromCurrencyId)
                .orElseThrow(() -> new RuntimeException("Курс не найден для валюты id=" + fromCurrencyId))
                .getRateToBase();

        BigDecimal rateTo = currencyRatesRepository.findByCurrencyId(toCurrencyId)
                .orElseThrow(() -> new RuntimeException("Курс не найден для валюты id=" + toCurrencyId))
                .getRateToBase();
        return amount.multiply(rateFrom).divide(rateTo, 2, RoundingMode.HALF_UP);
    }
}
