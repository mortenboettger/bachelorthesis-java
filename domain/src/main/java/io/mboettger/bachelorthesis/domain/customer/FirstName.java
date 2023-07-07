package io.mboettger.bachelorthesis.domain.customer;

public record FirstName(String value) {
    public FirstName {
        if (value.isBlank()) throw new IllegalStateException("Firstname should not be blank");
        if (Character.isLowerCase(value.charAt(0))) throw new IllegalStateException("First character of firstname must be uppercase");
        if (value.length() < 2) throw new IllegalStateException("Firstname must contain at least two characters");
        if (!value.matches("^[a-zA-Z\\- ]+$")) throw new IllegalStateException("Malformed firstname");
    }
}
