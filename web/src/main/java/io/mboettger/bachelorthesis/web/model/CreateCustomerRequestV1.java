package io.mboettger.bachelorthesis.web.model;

import java.io.Serializable;

public record CreateCustomerRequestV1(
        String firstName,
        String lastName,
        AddressV1 address,
        String phoneNumber,
        String emailAddress
) implements Serializable { }
