package io.mboettger.bachelorthesis.persistence.memory;

import io.mboettger.bachelorthesis.domain.customer.*;
import io.mboettger.bachelorthesis.domain.customer.address.*;
import io.mboettger.bachelorthesis.persistence.gateway.CustomerGateway;
import io.mboettger.bachelorthesis.persistence.memory.entity.CustomerEntity;
import org.hibernate.SessionFactory;

import static io.mboettger.bachelorthesis.persistence.memory.helper.PersistenceHelper.throwIfNull;

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
        entity.setStreet(data.getAddress().getStreet().value());
        entity.setHouseNumber(data.getAddress().getHouseNumber().value());
        entity.setHouseNumberAddition(
                data.getAddress().getHouseNumberAddition().map(HouseNumberAddition::value).orElse(null)
        );
        entity.setPostCode(data.getAddress().getPostCode().value());
        entity.setCity(data.getAddress().getCity().value());
        entity.setDistrict(data.getAddress().getDistrict().map(District::value).orElse(null));
        entity.setPhoneNumber(data.getPhoneNumber().map(PhoneNumber::value).orElse(null));
        entity.setEmailAddress(data.getEmailAddress().map(EmailAddress::value).orElse(null));

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
                        data.getHouseNumberAddition().map(HouseNumberAddition::new).orElse(null),
                        new PostCode(data.getPostCode()),
                        new City(data.getCity()),
                        data.getDistrict().map(District::new).orElse(null)
                ),
                data.getPhoneNumber().map(PhoneNumber::new).orElse(null),
                data.getEmailAddress().map(EmailAddress::new).orElse(null)
        );
    }


    @Override
    public Customer findByEmail(EmailAddress emailAddress) {
        throwIfNull(emailAddress);

        return withTransaction(session -> queryWithCriteria(session, criteriaQuery -> {
            var root = criteriaQuery.from(entityClass);
            return criteriaQuery.where(
                    root.get("emailAddress").in(emailAddress.value()) // no reflection possible
            );
        })).getResultList().stream().map(this::toDomain).findFirst().orElse(null);
    }
}
