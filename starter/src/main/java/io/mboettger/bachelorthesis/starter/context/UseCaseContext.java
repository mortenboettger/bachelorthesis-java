package io.mboettger.bachelorthesis.starter.context;

import io.mboettger.bachelorthesis.persistence.gateway.GatewayFactory;
import io.mboettger.bachelorthesis.usecase.boundary.UseCaseFactory;
import io.mboettger.bachelorthesis.usecase.impl.factory.UseCaseFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseContext {

    @Bean
    public UseCaseFactory useCaseFactory(GatewayFactory gatewayFactory) {
        return new UseCaseFactoryImpl(gatewayFactory);
    }
}
