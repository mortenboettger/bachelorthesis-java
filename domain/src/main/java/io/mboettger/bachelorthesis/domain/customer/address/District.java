package io.mboettger.bachelorthesis.domain.customer.address;

public record District(String value) {
    public District {
        if (value.isBlank()) throw new IllegalStateException("District should not be blank");
        if (value.chars().anyMatch(Character::isDigit)) throw new IllegalStateException("District should not contain digits");
    }
}
