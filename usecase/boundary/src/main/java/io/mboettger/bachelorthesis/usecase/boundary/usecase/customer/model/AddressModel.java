package io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model;

import java.util.Optional;

public class AddressModel {

    private final String street;
    private final String houseNumber;
    private final String houseNumberAddition;
    private final String postCode;
    private final String city;
    private final String district;

    public AddressModel(
            String street,
            String houseNumber,
            String houseNumberAddition,
            String postCode,
            String city,
            String district
    ) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.houseNumberAddition = houseNumberAddition;
        this.postCode = postCode;
        this.city = city;
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public Optional<String> getHouseNumberAddition() {
        return Optional.ofNullable(this.houseNumberAddition);
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    public Optional<String> getDistrict() {
        return Optional.ofNullable(this.district);
    }
}
