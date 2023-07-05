package io.mboettger.bachelorthesis.domain.customer.address;

import java.util.Optional;

public record Address(
        Street street,
        HouseNumber houseNumber,
        HouseNumberAddition houseNumberAddition,
        PostCode postCode,
        City city,
        District district
) {
    public Optional<HouseNumberAddition> getHouseNumberAddition() {
        return Optional.ofNullable(this.houseNumberAddition);
    }

    public Optional<District> getDistrict() {
        return Optional.ofNullable(this.district);
    }
}
