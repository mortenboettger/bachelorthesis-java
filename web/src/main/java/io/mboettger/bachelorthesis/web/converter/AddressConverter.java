package io.mboettger.bachelorthesis.web.converter;

import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model.AddressModel;
import io.mboettger.bachelorthesis.web.model.AddressV1;

public final class AddressConverter {
    private AddressConverter() { }

    public static AddressV1 toWebV1(AddressModel address) {
        return new AddressV1(
                address.street(),
                address.houseNumber(),
                address.houseNumberAddition(),
                address.postCode(),
                address.city(),
                address.district()
        );
    }

    public static AddressModel toBoundary(AddressV1 address) {
        return new AddressModel(
                address.street(),
                address.houseNumber(),
                address.houseNumberAddition(),
                address.postCode(),
                address.city(),
                address.district()
        );
    }
}
