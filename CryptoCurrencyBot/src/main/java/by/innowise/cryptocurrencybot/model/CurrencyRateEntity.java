package by.innowise.cryptocurrencybot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "currency_rates")
public class CurrencyRateEntity {

    @Id
    private String symbol;

    private BigDecimal price;
}

