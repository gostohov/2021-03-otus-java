package ru.otus.core.repository;

import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class DataTemplateHibernate<T> implements DataTemplate<T> {

    private final Class<T> clazz;

    public DataTemplateHibernate(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Optional<T> findById(Session session, long id) {
        return Optional.ofNullable(session.find(clazz, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <F> Optional<T> findByField(Session session, Field<F> field) {
        Query query = session.createQuery(String.format("from %s where %s = '%s'", clazz.getSimpleName(), field.getName(), field.getValue()), clazz);
        return (Optional<T>) query.getResultStream().findFirst();
    }

    @Override
    public List<T> findAll(Session session) {
        return session.createQuery(String.format("from %s", clazz.getSimpleName()), clazz).getResultList();
    }

    @Override
    public void insert(Session session, T object) {
        session.persist(object);
    }

    @Override
    public void update(Session session, T object) {
        session.merge(object);
    }
}
