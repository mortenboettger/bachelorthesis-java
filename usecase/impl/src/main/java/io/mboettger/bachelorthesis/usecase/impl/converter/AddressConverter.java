package io.mboettger.bachelorthesis.usecase.impl.converter;

import io.mboettger.bachelorthesis.domain.customer.address.*;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model.AddressModel;

public final class AddressConverter {
    private AddressConverter() { }

    public static Address toDomain(AddressModel address) {
        return new Address(
                new Street(address.getStreet()),
                new HouseNumber(address.getHouseNumber()),
                address.getHouseNumberAddition().map(HouseNumberAddition::new).orElse(null),
                new PostCode(address.getPostCode()),
                new City(address.getCity()),
                address.getDistrict().map(District::new).orElse(null)
        );
    }

    public static AddressModel toBoundary(Address address) {
        return new AddressModel(
                address.getStreet().value(),
                address.getHouseNumber().value(),
                address.getHouseNumberAddition().map(HouseNumberAddition::value).orElse(null),
                address.getPostCode().value(),
                address.getCity().value(),
                address.getDistrict().map(District::value).orElse(null)
        );
    }
}
