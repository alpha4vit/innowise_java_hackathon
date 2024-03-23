package by.innowise.cryptocurrencybot.notification;

import by.innowise.cryptocurrencybot.currency.UpdatedCurrency;

import java.util.List;

public interface NotificationService {
    void sendMessageAboutRateUpdate(List<UpdatedCurrency> updatedCurrencies);
}
