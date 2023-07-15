package io.mboettger.bachelorthesis.web.model;

import java.io.Serializable;

public record AddressV1(
        String street,
        String houseNumber,
        String houseNumberAddition,
        String postCode,
        String city,
        String district
) implements Serializable { }
