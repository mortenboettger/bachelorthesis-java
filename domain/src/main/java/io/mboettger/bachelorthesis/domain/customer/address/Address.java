package io.mboettger.bachelorthesis.domain.customer.address;

public record Address(
        Street street,
        HouseNumber houseNumber,
        HouseNumberAddition houseNumberAddition,
        PostCode postCode,
        City city,
        District district
) {
}
