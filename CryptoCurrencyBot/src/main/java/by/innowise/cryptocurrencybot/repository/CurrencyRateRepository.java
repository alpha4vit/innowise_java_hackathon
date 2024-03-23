package by.innowise.cryptocurrencybot.repository;

import by.innowise.cryptocurrencybot.model.CurrencyRateEntity;

import java.util.List;
import java.util.Optional;

public interface CurrencyRateRepository {

    void updateCurrencyRates(List<CurrencyRateEntity> currencyRateEntities);

    void updateCurrencyRate(CurrencyRateEntity currencyRateEntity);

    Optional<CurrencyRateEntity> findBySymbol(String symbol);

    List<CurrencyRateEntity> findAll();

}
