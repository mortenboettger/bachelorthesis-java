package io.mboettger.bachelorthesis.persistence.memory;

import io.mboettger.bachelorthesis.domain.customer.*;
import io.mboettger.bachelorthesis.domain.customer.address.*;
import io.mboettger.bachelorthesis.persistence.gateway.CustomerGateway;
import io.mboettger.bachelorthesis.persistence.memory.entity.CustomerEntity;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;

import java.util.stream.Stream;

public class CustomerGatewayImpl extends ReadWriteGatewayImpl<Customer, CustomerEntity> implements CustomerGateway {
    public CustomerGatewayImpl(EntityManager entityManager, SessionFactory sessionFactory) {
        super(entityManager, sessionFactory, CustomerEntity.class);
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
        return null;
    }

    @Override
    public Stream<Customer> findByAddress(Address address) {
        return null;
    }
}
