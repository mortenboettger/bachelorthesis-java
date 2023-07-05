package io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show;

public record ShowCustomerByEmailAddressRequest(String emailAddress) implements ShowCustomerRequest { }
