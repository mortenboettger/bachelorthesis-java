package io.mboettger.bachelorthesis.persistence.gateway;

public interface GatewayFactory {

    <T extends Gateway> T make(Class<T> gateway);
    void migrate();
}
