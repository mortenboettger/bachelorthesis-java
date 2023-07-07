package io.mboettger.bachelorthesis.domain.customer;

public record LastName(String value) {
    public LastName {
        if (value.isBlank()) throw new IllegalStateException("Lastname should not be blank");
        if (Character.isLowerCase(value.charAt(0))) throw new IllegalStateException("First character of lastname must be uppercase");
        if (value.length() < 2) throw new IllegalStateException("Lastname must contain at least two characters");
        if (!value.matches("^[a-zA-Z\\- ]+$")) throw new IllegalStateException("Malformed lastname");
    }
}
