package by.innowise.cryptocurrencybot.model;


import by.innowise.cryptocurrencybot.utils.enums.BotState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    private Long chatId;

    private String userName;

    private String firstName;

    private Double rateDiff;

    @Enumerated(EnumType.ORDINAL)
    private BotState botState;

}
