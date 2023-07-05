package io.mboettger.bachelorthesis.web.model;

import java.io.Serializable;

public record CustomerResponseV1(
        String id,
        String firstName,
        String lastName,
        AddressV1 address,
        String phoneNumber,
        String emailAddress
) implements Serializable { }
