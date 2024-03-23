package by.innowise.cryptocurrencybot.repository;

import by.innowise.cryptocurrencybot.model.UserEntity;
import by.innowise.cryptocurrencybot.utils.enums.BotState;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByChatId(Long chatId);

    void save(UserEntity userEntity);

    void setBotState(UserEntity userEntity, BotState botState);

    List<UserEntity> findAll();

    void update(UserEntity entity);

}
