package by.innowise.cryptocurrencybot.notification.impl;

import by.innowise.cryptocurrencybot.bot.CryptoCurrencyBot;
import by.innowise.cryptocurrencybot.currency.RatioCalculator;
import by.innowise.cryptocurrencybot.currency.UpdatedCurrency;
import by.innowise.cryptocurrencybot.notification.NotificationService;
import by.innowise.cryptocurrencybot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final UserService userService;
    private final CryptoCurrencyBot cryptoCurrencyBot;

    @Override
    public void sendMessageAboutRateUpdate(List<UpdatedCurrency> updatedCurrencies) {
        var users = userService.getAll();
        if (!users.isEmpty()) {
            var sendMessage = new SendMessage();
            var messageText = new StringBuilder("Updated rates: ");
            users.forEach(user -> {
                updatedCurrencies.stream()
                        .filter(rate ->
                        {
                            var ratio = RatioCalculator.calculateRatio(rate.getPreviousPrice(), rate.getCurrentPrice());
                            return ratio.compareTo(BigDecimal.valueOf(user.getRateDiff())) >= 0;
                        })
                        .forEach(rate ->
                        {
                            sendMessage.setChatId(user.getChatId());
                            messageText.append(String.format("\n%s - %s", rate.getSymbol(), rate.getCurrentPrice()));
                            sendMessage.setText(messageText.toString());
                            try {
                                cryptoCurrencyBot.execute(sendMessage);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        });
            });
        }
    }
}
