package io.mboettger.bachelorthesis.web.model;

import java.io.Serializable;
import java.util.Optional;

public record AddressV1(
        String street,
        String houseNumber,
        String houseNumberAddition,
        String postCode,
        String city,
        String district
) implements Serializable {
    public Optional<String> getHouseNumberAddition() {
        return Optional.ofNullable(this.houseNumberAddition);
    }

    public Optional<String> getDistrict() {
        return Optional.ofNullable(this.district);
    }
}
