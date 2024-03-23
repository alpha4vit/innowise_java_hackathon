package by.innowise.cryptocurrencybot.service;

import by.innowise.cryptocurrencybot.model.UserEntity;
import by.innowise.cryptocurrencybot.utils.enums.BotState;

import java.util.List;

public interface UserService {

    UserEntity getByUsername(String username);

    UserEntity getByChatId(Long chatId);

    void save(UserEntity entity);

    void setBotState(UserEntity entity, BotState botState);

    void update(UserEntity entity);

    List<UserEntity> getAll();

}
