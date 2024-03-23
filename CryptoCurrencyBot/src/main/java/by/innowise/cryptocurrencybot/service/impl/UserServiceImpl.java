package by.innowise.cryptocurrencybot.service.impl;

import by.innowise.cryptocurrencybot.model.UserEntity;
import by.innowise.cryptocurrencybot.repository.UserRepository;
import by.innowise.cryptocurrencybot.service.UserService;
import by.innowise.cryptocurrencybot.utils.enums.BotState;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity getByUsername(String username) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity getByChatId(Long chatId) {
        return userRepository.findByChatId(chatId)
                .orElseThrow(() -> new EntityNotFoundException("User with this chatId not found!"));
    }

    @Override
    @Transactional
    public void save(UserEntity entity) {
        userRepository.save(entity);
    }

    @Override
    @Transactional
    public void setBotState(UserEntity entity, BotState botState) {
        userRepository.setBotState(entity, botState);
    }

    @Override
    public void update(UserEntity entity) {
        userRepository.update(entity);
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

}
