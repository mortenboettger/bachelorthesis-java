package io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model;

import java.util.Optional;

public record CustomerModel(
        String id,
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
