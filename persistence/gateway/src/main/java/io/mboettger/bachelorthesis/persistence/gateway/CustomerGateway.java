package io.mboettger.bachelorthesis.persistence.gateway;

import io.mboettger.bachelorthesis.domain.customer.Customer;
import io.mboettger.bachelorthesis.domain.customer.EmailAddress;

public interface CustomerGateway extends ReadWriteGateway<Customer> {
    Customer findByEmail(EmailAddress emailAddress);
}
