package io.mboettger.bachelorthesis.domain.customer;

public record PhoneNumber(String value) {
    public PhoneNumber {
        if (value.isBlank()) throw new IllegalStateException("Phone-number should not be blank");
        if (!value.matches("^\\+?[1-9][0-9]{7,}$")) throw new IllegalStateException("Malformed phone-number");
    }
}
