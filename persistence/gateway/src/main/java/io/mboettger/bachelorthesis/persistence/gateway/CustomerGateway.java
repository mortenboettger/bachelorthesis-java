package io.mboettger.bachelorthesis.persistence.gateway;

import io.mboettger.bachelorthesis.domain.customer.Customer;
import io.mboettger.bachelorthesis.domain.customer.EmailAddress;
import io.mboettger.bachelorthesis.domain.customer.address.Address;

import java.util.stream.Stream;

public interface CustomerGateway extends ReadWriteGateway<Customer> {
    Customer findByEmail(EmailAddress emailAddress);
    Stream<Customer> findByAddress(Address address);
}
