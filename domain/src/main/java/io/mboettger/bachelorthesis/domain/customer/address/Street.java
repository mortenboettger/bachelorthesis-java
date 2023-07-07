package io.mboettger.bachelorthesis.domain.customer.address;

public record Street(String value) {
    public Street {
        if (value.isBlank()) throw new IllegalStateException("Street should not be blank");
        if (value.chars().anyMatch(Character::isDigit)) throw new IllegalStateException("Street should not contain digits");
    }
}
