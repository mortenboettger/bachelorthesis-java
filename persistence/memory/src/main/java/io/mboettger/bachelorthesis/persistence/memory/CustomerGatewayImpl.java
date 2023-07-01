package io.mboettger.bachelorthesis.persistence.memory;

import io.mboettger.bachelorthesis.domain.customer.*;
import io.mboettger.bachelorthesis.domain.customer.address.*;
import io.mboettger.bachelorthesis.persistence.gateway.CustomerGateway;
import io.mboettger.bachelorthesis.persistence.memory._helper.PersistenceHelper;
import io.mboettger.bachelorthesis.persistence.memory.entity.CustomerEntity;
import jakarta.persistence.Persistence;
import org.hibernate.SessionFactory;

import java.util.Arrays;
import java.util.stream.Stream;

import static io.mboettger.bachelorthesis.persistence.memory._helper.PersistenceHelper.throwIfNull;

public class CustomerGatewayImpl extends ReadWriteGatewayImpl<Customer, CustomerEntity> implements CustomerGateway {
    public CustomerGatewayImpl(SessionFactory sessionFactory) {
        super(sessionFactory, CustomerEntity.class);
    }

    @Override
    protected CustomerEntity toEntity(Customer data) {
        var entity = new CustomerEntity();

        entity.setId(data.getId());
        entity.setFirstName(data.getFirstName().value());
        entity.setLastName(data.getLastName().value());
        entity.setStreet(data.getAddress().street().value());
        entity.setHouseNumber(data.getAddress().houseNumber().value());
        entity.setHouseNumberAddition(data.getAddress().houseNumberAddition().value()); //null
        entity.setPostCode(data.getAddress().postCode().value());
        entity.setCity(data.getAddress().city().value());
        entity.setDistrict(data.getAddress().district().value()); //null

        return entity;
    }

    @Override
    protected Customer toDomain(CustomerEntity data) {
        return new Customer(
                data.getId(),
                new FirstName(data.getFirstName()),
                new LastName(data.getLastName()),
                new Address(
                        new Street(data.getStreet()),
                        new HouseNumber(data.getHouseNumber()),
                        new HouseNumberAddition(data.getHouseNumberAddition()),
                        new PostCode(data.getPostCode()),
                        new City(data.getCity()),
                        new District(data.getDistrict())
                ),
                new PhoneNumber(data.getPhoneNumber()),
                new EmailAddress(data.getEmailAddress())
        );
    }


    @Override
    public Customer findByEmail(EmailAddress emailAddress) {
        throwIfNull(emailAddress);

        var entity = withTransaction(session -> queryWithCriteria(session, criteriaQuery -> {
            var root = criteriaQuery.from(entityClass);
            return criteriaQuery.where(
                    root.get("emailAddress").in(emailAddress.value())
            );
        })).getResultList().stream().findFirst().orElse(null);

        if (entity != null) {
            return toDomain(entity);
        } else return null;
    }

    @Override
    public Stream<Customer> findByAddress(Address address) {
        return null;
    }
}
