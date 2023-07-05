package io.mboettger.bachelorthesis.domain.customer;

import io.mboettger.bachelorthesis.domain.DomainModel;
import io.mboettger.bachelorthesis.domain.customer.address.Address;

import java.util.Optional;

public class Customer extends DomainModel {
    private final FirstName firstName;
    private final LastName lastName;
    private final Address address;
    private final PhoneNumber phoneNumber;
    private final EmailAddress emailAddress;

    public Customer(
            String id,
            FirstName firstName,
            LastName lastName,
            Address address,
            PhoneNumber phoneNumber,
            EmailAddress emailAddress
    ) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public LastName getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public Optional<PhoneNumber> getPhoneNumber() {
        return Optional.ofNullable(phoneNumber);
    }

    public Optional<EmailAddress> getEmailAddress() {
        return Optional.ofNullable(emailAddress);
    }
}
