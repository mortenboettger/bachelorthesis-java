package io.mboettger.bachelorthesis.usecase.impl.converter;

import io.mboettger.bachelorthesis.domain.customer.address.*;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model.AddressModel;

public final class AddressConverter {
    private AddressConverter() { }

    public static Address toDomain(AddressModel address) {
        return new Address(
                new Street(address.street()),
                new HouseNumber(address.houseNumber()),
                address.getHouseNumberAddition().map(HouseNumberAddition::new).orElse(null),
                new PostCode(address.postCode()),
                new City(address.city()),
                address.getDistrict().map(District::new).orElse(null)
        );
    }

    public static AddressModel toBoundary(Address address) {
        return new AddressModel(
                address.street().value(),
                address.houseNumber().value(),
                address.getHouseNumberAddition().map(HouseNumberAddition::value).orElse(null),
                address.postCode().value(),
                address.city().value(),
                address.getDistrict().map(District::value).orElse(null)
        );
    }
}
