package io.mboettger.bachelorthesis.web.model;

import java.io.Serializable;
import java.util.Optional;

public record CreateCustomerRequestV1(
        String firstName,
        String lastName,
        AddressV1 address,
        String phoneNumber,
        String emailAddress
) implements Serializable {
    public Optional<String> getPhoneNumber() {
        return Optional.ofNullable(this.phoneNumber);
    }

    public Optional<String> getEmailAddress() {
        return Optional.ofNullable(this.emailAddress);
    }
}
