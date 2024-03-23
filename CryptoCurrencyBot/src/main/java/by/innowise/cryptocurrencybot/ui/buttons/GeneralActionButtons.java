package by.innowise.cryptocurrencybot.ui.buttons;

import by.innowise.cryptocurrencybot.utils.enums.ActionType;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;


public class GeneralActionButtons {
    private static final InlineKeyboardButton RATE_BUTTON = new InlineKeyboardButton("Check pair rate");
    private static final InlineKeyboardButton FAVOURITE_BUTTON = new InlineKeyboardButton("Check favourites pairs");
    private static final InlineKeyboardButton SUBSCRIBE_BUTTON = new InlineKeyboardButton("Subscribe crypto pair");
    private static final InlineKeyboardButton RATE_DIFF_BUTTON = new InlineKeyboardButton("Insert rate difference");


    static {
        RATE_BUTTON.setCallbackData(ActionType.RATE_ACTION.name());
        FAVOURITE_BUTTON.setCallbackData(ActionType.FAVOURITE_LIST_ACTION.name());
        SUBSCRIBE_BUTTON.setCallbackData(ActionType.SUBSCRIBE_ACTION.name());
        RATE_DIFF_BUTTON.setCallbackData(ActionType.RATE_DIFF_BUTTON.name());
    }

    public static InlineKeyboardMarkup actionChooseButtons(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> buttons = List.of(RATE_BUTTON, FAVOURITE_BUTTON, SUBSCRIBE_BUTTON, RATE_DIFF_BUTTON);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        buttons.forEach(el -> keyboard.add(List.of(el)));
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

}
