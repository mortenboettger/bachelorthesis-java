package io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create;

import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model.AddressModel;

import java.util.Optional;

public record CreateCustomerRequest(
        String firstName,
        String lastName,
        AddressModel address,
        String phoneNumber,
        String emailAddress
) {
    public Optional<String> getPhoneNumber() {
        return Optional.ofNullable(this.phoneNumber);
    }

    public Optional<String> getEmailAddress() {
        return Optional.ofNullable(this.emailAddress);
    }
}
