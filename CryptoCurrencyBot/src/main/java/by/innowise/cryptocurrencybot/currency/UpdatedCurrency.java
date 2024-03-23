package by.innowise.cryptocurrencybot.currency;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdatedCurrency {
    private String symbol;
    private BigDecimal currentPrice;
    private BigDecimal previousPrice;
}
