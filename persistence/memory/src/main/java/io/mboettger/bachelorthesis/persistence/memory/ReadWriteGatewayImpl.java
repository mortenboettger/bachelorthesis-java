package io.mboettger.bachelorthesis.persistence.memory;

import io.mboettger.bachelorthesis.domain.DomainModel;
import io.mboettger.bachelorthesis.persistence.gateway.ReadWriteGateway;
import io.mboettger.bachelorthesis.persistence.memory.entity.EntityModel;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.function.Function;
import java.util.stream.Stream;

import static io.mboettger.bachelorthesis.persistence.memory._helper.PersistenceHelper.throwIfNull;

abstract class ReadWriteGatewayImpl<T extends DomainModel, E extends EntityModel> implements ReadWriteGateway<T> {

    protected final SessionFactory sessionFactory;
    protected final Class<E> entityClass;

    protected ReadWriteGatewayImpl(SessionFactory sessionFactory, Class<E> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    protected abstract E toEntity(T data);

    protected abstract T toDomain(E data);

    @Override
    public T findOne(String id) {
        throwIfNull(id);

        var entity = withTransaction(session -> session.find(entityClass, id));
        if (entity != null) {
            return toDomain(entity);
        } else return null;
    }

    @Override
    public Stream<T> findAll() {
        return withTransaction(session -> queryWithCriteria(session, criteriaQuery -> criteriaQuery.select(criteriaQuery.from(entityClass))).getResultStream().map(this::toDomain));
    }

    @Override
    public T save(T data) {
        throwIfNull(data);

        var entity = withTransaction(session -> session.merge(toEntity(data)));
        if (entity != null) {
            return toDomain(entity);
        } else return null;
    }

    @Override
    public void delete(String id) {
        throwIfNull(id);

        withTransaction(session -> {
            var entity = findOne(id);
            if (entity != null) {
                session.remove(entity);
            }
            return entity;
        });
    }

    @Override
    public void delete(T data) {
        throwIfNull(data);

        delete(data.getId());
    }

    @Override
    public boolean exists(String id) {
        throwIfNull(id);

        return findOne(id) != null;
    }

    protected <R> TypedQuery<R> query(Session session, Function<CriteriaBuilder, CriteriaQuery<R>> callable) {
        return session.createQuery(callable.apply(session.getCriteriaBuilder()));
    }

    protected <R> TypedQuery<R> queryWithCriteria(Session session, Function<CriteriaQuery<E>, CriteriaQuery<R>> callable) {
        return query(session, criteriaBuilder -> callable.apply(criteriaBuilder.createQuery(entityClass)));
    }

    protected <R> R withTransaction(Function<Session, R> callable) {
        var session = ThreadLocalSessionFactory.getOrCreate(sessionFactory);

        if (session.getTransaction().isActive()) {
            return callable.apply(session);
        } else {
            session.getTransaction().begin();
            try {
                var result = callable.apply(session);
                session.getTransaction().commit();
                return result;
            } catch (Exception e) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                throw e;
            }
        }
    }
}
