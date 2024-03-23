package by.innowise.cryptocurrencybot.bot;

import by.innowise.cryptocurrencybot.config.BotConfig;
import by.innowise.cryptocurrencybot.handler.AnswerHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CryptoCurrencyBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final AnswerHandler answerHandler;

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        log.info("dsa");
        if (update.hasMessage() && update.getMessage().hasText())
            execute(answerHandler.answer(update.getMessage()));
        if (update.hasCallbackQuery())
            execute(answerHandler.answer(update.getCallbackQuery()));
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }
}
