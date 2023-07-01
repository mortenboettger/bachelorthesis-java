package io.mboettger.bachelorthesis.persistence.gateway;

import io.mboettger.bachelorthesis.domain.DomainModel;

import java.util.stream.Stream;

public interface ReadWriteGateway<T extends DomainModel> extends Gateway {

    T findOne(String id);

    Stream<T> findAll();

    T save(T data);

    void delete(String id);

    void delete(T data);

    boolean exists(String id);
}
