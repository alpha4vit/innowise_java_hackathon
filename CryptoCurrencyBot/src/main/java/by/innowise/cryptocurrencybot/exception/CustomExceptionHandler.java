package by.innowise.cryptocurrencybot.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler {


    @ExceptionHandler
    public void handle(Exception e){
        log.error(e.getMessage());
    }
}
