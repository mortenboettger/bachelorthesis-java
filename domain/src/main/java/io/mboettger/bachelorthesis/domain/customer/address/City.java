package io.mboettger.bachelorthesis.domain.customer.address;

public record City(String value) {
    public City {
        if (value.isBlank()) throw new IllegalStateException("City should not be blank");
        if (value.chars().anyMatch(Character::isDigit)) throw new IllegalStateException("City should not contain digits");
    }
}
