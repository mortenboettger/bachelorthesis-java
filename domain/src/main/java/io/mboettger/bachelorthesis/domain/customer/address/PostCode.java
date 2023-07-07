package io.mboettger.bachelorthesis.domain.customer.address;

public record PostCode(String value) {
    public PostCode {
        if (value.isBlank()) throw new IllegalStateException("Post code should not be blank");
        if (!value.chars().allMatch(Character::isDigit)) throw new IllegalStateException("Post code should only contain digits");
        if (value.length() != 5) throw new IllegalStateException("Post code should only contain exactly 5 digits");
    }
}
