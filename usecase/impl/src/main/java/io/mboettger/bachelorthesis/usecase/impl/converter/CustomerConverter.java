package io.mboettger.bachelorthesis.usecase.impl.converter;

import io.mboettger.bachelorthesis.domain.customer.*;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model.CustomerModel;

public final class CustomerConverter {
    private CustomerConverter() { }

    public static Customer toDomain(CustomerModel customer) {
        return new Customer(
                customer.id(),
                new FirstName(customer.firstName()),
                new LastName(customer.lastName()),
                AddressConverter.toDomain(customer.address()),
                customer.getPhoneNumber().map(PhoneNumber::new).orElse(null),
                customer.getEmailAddress().map(EmailAddress::new).orElse(null)
        );
    }

    public static CustomerModel toBoundary(Customer customer) {
        return new CustomerModel(
                customer.getId(),
                customer.getFirstName().value(),
                customer.getLastName().value(),
                AddressConverter.toBoundary(customer.getAddress()),
                customer.getPhoneNumber().map(PhoneNumber::value).orElse(null),
                customer.getEmailAddress().map(EmailAddress::value).orElse(null)
        );
    }
}
