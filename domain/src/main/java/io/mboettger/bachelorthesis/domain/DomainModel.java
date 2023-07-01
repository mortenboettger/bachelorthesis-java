package io.mboettger.bachelorthesis.domain;

public abstract class DomainModel {
    private final String id;

    protected DomainModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
