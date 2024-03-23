package by.innowise.cryptocurrencybot.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface AnswerHandler{
    SendMessage answer(Message message);

    SendMessage answer(CallbackQuery callbackQuery);
}
