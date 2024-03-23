package by.innowise.cryptocurrencybot.service;

import by.innowise.cryptocurrencybot.currency.UpdatedCurrency;
import by.innowise.cryptocurrencybot.model.CurrencyRateEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CurrencyRateService {
    List<UpdatedCurrency> updateCurrencyRates(List<CurrencyRateEntity> currencyRateEntities);

    CurrencyRateEntity  getBySymbol(String symbol);

    Map<String, BigDecimal> getAllAsMap();

}
