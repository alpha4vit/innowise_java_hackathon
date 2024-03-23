package by.innowise.cryptocurrencybot.repository.impl;

import by.innowise.cryptocurrencybot.model.UserEntity;
import by.innowise.cryptocurrencybot.repository.UserRepository;
import by.innowise.cryptocurrencybot.utils.enums.BotState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<UserEntity> findByChatId(Long chatId) {
        var session = sessionFactory.getCurrentSession();
        var res = session.get(UserEntity.class, chatId);
        return Optional.of(res);
    }

    @Override
    @Transactional
    public void save(UserEntity userEntity) {
        var session = sessionFactory.getCurrentSession();
        userEntity.setRateDiff(0.1);
        if (session.get(UserEntity.class, userEntity.getChatId()) == null)
            session.persist(userEntity);
    }

    @Override
    @Transactional
    public void setBotState(UserEntity userEntity, BotState botState) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("UPDATE UserEntity SET botState = :newState WHERE chatId = :chatId");
        query.setParameter("newState", botState);
        query.setParameter("chatId", userEntity.getChatId());
        query.executeUpdate();
    }

    @Override
    public List<UserEntity> findAll() {
        var session = sessionFactory.getCurrentSession();
        Query<UserEntity> query = session.createQuery("FROM UserEntity ", UserEntity.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(UserEntity entity) {
        var session = sessionFactory.getCurrentSession();
        session.merge(entity);
    }
}
