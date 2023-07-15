package io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create;

import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model.AddressModel;

import java.util.Optional;

public class CreateCustomerRequest {

    private final String firstName;
    private final String lastName;
    private final AddressModel address;
    private final String phoneNumber;
    private final String emailAddress;

    public CreateCustomerRequest(
            String firstName,
            String lastName,
            AddressModel address,
            String phoneNumber,
            String emailAddress
    ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
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
