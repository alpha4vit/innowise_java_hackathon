package by.innowise.cryptocurrencybot.handler;

import by.innowise.cryptocurrencybot.service.CurrencyRateService;
import by.innowise.cryptocurrencybot.service.UserService;
import by.innowise.cryptocurrencybot.ui.buttons.GeneralActionButtons;
import by.innowise.cryptocurrencybot.utils.enums.ActionType;
import by.innowise.cryptocurrencybot.utils.enums.BotState;
import by.innowise.cryptocurrencybot.utils.mapper.UserMapper;
import by.innowise.cryptocurrencybot.utils.parsers.MessageParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class AnswerHandlerImpl implements AnswerHandler {

    private final UserService userService;
    private final UserMapper userMapper;
    private final CurrencyRateService currencyRateService;
    private final MessageParser messageParser;

    public SendMessage answer(Message message) {
        SendMessage response = new SendMessage();
        switch (message.getText()) {
            case "/start" -> {
                response.setText(messageParser.readMessage("D:\\programming\\java proj\\innowise_java_hackathon\\CryptoCurrencyBot\\src\\main\\resources\\static\\messages\\welcome.html"));
                response.setReplyMarkup(GeneralActionButtons.actionChooseButtons());
                userService.save(userMapper.toEntity(message.getFrom(), message.getChatId()));
            }
            default -> {
                var user = userService.getByChatId(message.getChatId());
                switch (user.getBotState()) {
                    case BotState.WAITING_PAIR -> {
                        var currencyRateEntity = currencyRateService.getBySymbol(message.getText());
                        if (currencyRateEntity != null) {
                            response.setText(
                                    String.format("Rate:\n%s - %f",
                                            currencyRateEntity.getSymbol(),
                                            currencyRateEntity.getPrice())
                            );
                            userService.setBotState(user, BotState.START);
                            response.setReplyMarkup(GeneralActionButtons.actionChooseButtons());
                        }
                        else
                            response.setText("Present existing pair!");
                    }
                    case BotState.WAITING_PRICE_DIFF -> {
                        var value = Double.parseDouble(message.getText());
                        if (value > 0 && value < 100) {
                            user.setRateDiff(value);
                            userService.update(user);
                            userService.setBotState(user, BotState.START);
                            response.setReplyMarkup(GeneralActionButtons.actionChooseButtons());
                        }
                        else
                            response.setText("Invalid value!");
                    }
                }
            }
        }
        response.setParseMode("HTML");
        response.setChatId(message.getChatId());
        return response;
    }

    @Override
    public SendMessage answer(CallbackQuery callbackQuery) {
        SendMessage response = new SendMessage();
        switch (ActionType.valueOf(callbackQuery.getData())) {
            case ActionType.RATE_ACTION -> {
                userService.setBotState(userMapper.toEntity(callbackQuery.getFrom(), callbackQuery.getMessage().getChat().getId()), BotState.WAITING_PAIR);
                response.setText("Insert pair symbol in format: HEXUSDT");
            }
            case ActionType.RATE_DIFF_BUTTON -> {
                userService.setBotState(userMapper.toEntity(callbackQuery.getFrom(), callbackQuery.getMessage().getChat().getId()), BotState.WAITING_PRICE_DIFF);
                response.setText("Insert price difference in percents as a number: 5");
            }
            case ActionType.FAVOURITE_LIST_ACTION -> {
                response.setText("Not implemented yet");
                response.setReplyMarkup(GeneralActionButtons.actionChooseButtons());
            }
            case ActionType.SUBSCRIBE_ACTION -> {
                response.setText("Not implemented yet");
                response.setReplyMarkup(GeneralActionButtons.actionChooseButtons());
            }
        }
        response.setChatId(callbackQuery.getMessage().getChat().getId());
        response.setParseMode("HTML");
        return response;
    }
}