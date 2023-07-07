package io.mboettger.bachelorthesis.domain.customer.address;

import java.util.Optional;

public class Address {

    private final Street street;
    private final HouseNumber houseNumber;
    private final HouseNumberAddition houseNumberAddition;
    private final PostCode postCode;
    private final City city;
    private final District district;

    public Address(
            Street street,
            HouseNumber houseNumber,
            HouseNumberAddition houseNumberAddition,
            PostCode postCode,
            City city,
            District district
    ) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.houseNumberAddition = houseNumberAddition;
        this.postCode = postCode;
        this.city = city;
        this.district = district;
    }

    public Street getStreet() {
        return street;
    }

    public HouseNumber getHouseNumber() {
        return houseNumber;
    }

    public Optional<HouseNumberAddition> getHouseNumberAddition() {
        return Optional.ofNullable(this.houseNumberAddition);
    }

    public PostCode getPostCode() {
        return postCode;
    }

    public City getCity() {
        return city;
    }

    public Optional<District> getDistrict() {
        return Optional.ofNullable(this.district);
    }
}
