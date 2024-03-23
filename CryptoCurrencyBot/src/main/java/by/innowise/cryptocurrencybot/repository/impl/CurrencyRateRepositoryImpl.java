package by.innowise.cryptocurrencybot.repository.impl;

import by.innowise.cryptocurrencybot.model.CurrencyRateEntity;
import by.innowise.cryptocurrencybot.repository.CurrencyRateRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CurrencyRateRepositoryImpl implements CurrencyRateRepository {

    private final SessionFactory sessionFactory;

    @Override
    public void updateCurrencyRates(List<CurrencyRateEntity> currencyRateEntities) {
        var session = sessionFactory.getCurrentSession();
        currencyRateEntities.forEach(session::merge);
    }

    @Override
    public void updateCurrencyRate(CurrencyRateEntity currencyRateEntity) {
        var session = sessionFactory.getCurrentSession();
        session.merge(currencyRateEntity);
    }

    @Override
    public Optional<CurrencyRateEntity> findBySymbol(String symbol) {
        var session = sessionFactory.getCurrentSession();
        var currencyRateEntity = session.get(CurrencyRateEntity.class, symbol);
        return Optional.ofNullable(currencyRateEntity);
    }

    @Override
    public List<CurrencyRateEntity> findAll() {
        var session = sessionFactory.getCurrentSession();
        Query<CurrencyRateEntity> query = session.createQuery("FROM CurrencyRateEntity ", CurrencyRateEntity.class);
        return query.getResultList();
    }

}
