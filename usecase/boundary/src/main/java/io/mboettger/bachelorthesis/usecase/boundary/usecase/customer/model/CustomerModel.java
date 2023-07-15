package io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model;

import java.util.Optional;

public class CustomerModel {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final AddressModel address;
    private final String phoneNumber;
    private final String emailAddress;

    public CustomerModel(
            String id,
            String firstName,
            String lastName,
            AddressModel address,
            String phoneNumber,
            String emailAddress
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public AddressModel getAddress() {
        return address;
    }

    public Optional<String> getPhoneNumber() {
        return Optional.ofNullable(this.phoneNumber);
    }

    public Optional<String> getEmailAddress() {
        return Optional.ofNullable(this.emailAddress);
    }
}
