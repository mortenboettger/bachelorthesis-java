package io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model;

import java.util.Optional;

public record AddressModel(
        String street,
        String houseNumber,
        String houseNumberAddition,
        String postCode,
        String city,
        String district
) {
    public Optional<String> getHouseNumberAddition() {
        return Optional.ofNullable(this.houseNumberAddition);
    }

    public Optional<String> getDistrict() {
        return Optional.ofNullable(this.district);
    }
}
