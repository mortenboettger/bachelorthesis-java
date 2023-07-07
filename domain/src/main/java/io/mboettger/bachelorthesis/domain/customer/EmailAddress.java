package io.mboettger.bachelorthesis.domain.customer;

public record EmailAddress(String value) {
    public EmailAddress {
        if (value.isBlank()) throw new IllegalStateException("E-Mail address should not be blank");
        if (!value.matches("^[\\w\\-+.]+@(?>[a-zA-Z0-9\\-]+\\.)+(?>[a-zA-Z0-9]+)$")) throw new IllegalStateException("E-Mail address malformed");
    }
}
