package io.mboettger.bachelorthesis.domain.customer.address;

public record HouseNumberAddition(String value) {
    public HouseNumberAddition {
        if (value.isBlank()) throw new IllegalStateException("House number addition should not be blank");
        if (!value.matches("^[\\w\\-+]+$")) throw new IllegalStateException("Malformed house number addition");
    }
}
