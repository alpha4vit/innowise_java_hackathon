package by.innowise.cryptocurrencybot.service.impl;

import by.innowise.cryptocurrencybot.currency.UpdatedCurrency;
import by.innowise.cryptocurrencybot.model.CurrencyRateEntity;
import by.innowise.cryptocurrencybot.repository.CurrencyRateRepository;
import by.innowise.cryptocurrencybot.service.CurrencyRateService;
import by.innowise.cryptocurrencybot.utils.mapper.CurrencyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyRateServiceImpl implements CurrencyRateService {

    private final CurrencyRateRepository currencyRateRepository;
    private final CurrencyMapper currencyMapper;

    @Override
    @Transactional
    public List<UpdatedCurrency> updateCurrencyRates(List<CurrencyRateEntity> currencyRateEntities) {
        var ratesBeforeUpdate = getAllAsMap();
        List<UpdatedCurrency> result = new ArrayList<>();
        if (!ratesBeforeUpdate.isEmpty()) {
            currencyRateEntities.forEach(rate -> {
                var priceBefore = ratesBeforeUpdate.get(rate.getSymbol());
                if (!priceBefore.equals(rate.getPrice())) {
                    currencyRateRepository.updateCurrencyRate(rate);
                    result.add(currencyMapper.toUpdatedCurrency(rate, priceBefore));
                }
            });
        }
        else
            currencyRateRepository.updateCurrencyRates(currencyRateEntities);
        return result; //TODO optimize
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencyRateEntity getBySymbol(String symbol) {
        return currencyRateRepository.findBySymbol(symbol)
                .orElse(null);
    }

    @Override
    public Map<String, BigDecimal> getAllAsMap() {
        record MapElement(String symbol, BigDecimal price){}
        return currencyRateRepository.findAll()
                .stream()
                .map(el -> new MapElement(el.getSymbol(), el.getPrice()))
                .collect(Collectors.toMap(MapElement::symbol, MapElement::price)
                );
    }
}
