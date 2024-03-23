package by.innowise.cryptocurrencybot.currency;

import by.innowise.cryptocurrencybot.model.CurrencyRateEntity;
import by.innowise.cryptocurrencybot.notification.NotificationService;
import by.innowise.cryptocurrencybot.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrencyUpdater {

    private final RestTemplate restTemplate;
    private final CurrencyRateService currencyRateService;
    private final NotificationService notificationService;

    @Value(value = "${currency-api.url}")
    private String currencyUrl;

    @Scheduled(fixedDelay = 15000)
    private void updateCurrencyRates(){
        log.info("Currency rates update started");
        var response = restTemplate.getForObject(currencyUrl, CurrencyRateEntity[].class);
        if (response != null) {
            var receivedRates = Arrays.asList(response);
            var updatedCurrencyRates = currencyRateService.updateCurrencyRates(receivedRates);
            notificationService.sendMessageAboutRateUpdate(updatedCurrencyRates);
            log.info("Currency rates updated");
        }
        else {
            throw new RuntimeException("Currency rates update error!");
        }
    }



}
