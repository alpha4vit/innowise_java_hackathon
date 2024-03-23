package by.innowise.cryptocurrencybot.exception;

import by.innowise.cryptocurrencybot.bot.CryptoCurrencyBot;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler {


    @ExceptionHandler
    public void handle(Exception e){
        log.error(e.getMessage());
    }
}
