package io.mboettger.bachelorthesis.domain.customer.address;

public record HouseNumber(String value) {
    public HouseNumber {
        if (value.isBlank()) throw new IllegalStateException("House number should not be blank");
        if (!value.chars().allMatch(Character::isDigit)) throw new IllegalStateException("House number should only contain digits");
    }
}
