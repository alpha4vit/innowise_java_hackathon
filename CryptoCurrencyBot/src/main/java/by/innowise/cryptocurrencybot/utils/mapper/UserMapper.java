package by.innowise.cryptocurrencybot.utils.mapper;

import by.innowise.cryptocurrencybot.model.UserEntity;
import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.objects.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(User user, Long chatId);

}
