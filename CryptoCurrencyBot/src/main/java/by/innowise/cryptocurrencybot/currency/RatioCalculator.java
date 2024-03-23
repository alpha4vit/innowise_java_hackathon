package by.innowise.cryptocurrencybot.currency;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class RatioCalculator {

    public static BigDecimal calculateRatio(BigDecimal prev, BigDecimal before){
        BigDecimal difference = before.subtract(prev);

        // Вычисление процентного изменения
        BigDecimal ratio = difference.divide(before, 10, BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal.valueOf(100));

        return ratio.abs();
    };
}
