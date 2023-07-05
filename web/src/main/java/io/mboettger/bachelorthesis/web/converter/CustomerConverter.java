package io.mboettger.bachelorthesis.web.converter;

import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create.CreateCustomerRequest;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model.CustomerModel;
import io.mboettger.bachelorthesis.web.model.CreateCustomerRequestV1;
import io.mboettger.bachelorthesis.web.model.CustomerResponseV1;

public final class CustomerConverter {
    private CustomerConverter() { }

    public static CustomerResponseV1 toCustoemrResponseV1(CustomerModel customer) {
        return new CustomerResponseV1(
                customer.id(),
                customer.firstName(),
                customer.lastName(),
                AddressConverter.toWebV1(customer.address()),
                customer.phoneNumber(),
                customer.emailAddress()
        );
    }

    public static CreateCustomerRequest toCreateCustomerRequest(CreateCustomerRequestV1 request) {
        return new CreateCustomerRequest(
                request.firstName(),
                request.lastName(),
                AddressConverter.toBoundary(request.address()),
                request.phoneNumber(),
                request.emailAddress()
        );
    }
}
