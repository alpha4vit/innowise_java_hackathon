package by.innowise.cryptocurrencybot.utils.mapper;

import by.innowise.cryptocurrencybot.currency.UpdatedCurrency;
import by.innowise.cryptocurrencybot.model.CurrencyRateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    @Mapping(target = "currentPrice", source = "entity.price")
    UpdatedCurrency toUpdatedCurrency(CurrencyRateEntity entity, BigDecimal previousPrice);
}
