package io.mboettger.bachelorthesis.starter.context;

import io.mboettger.bachelorthesis.persistence.gateway.GatewayFactory;
import io.mboettger.bachelorthesis.persistence.memory.MemoryGatewayFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

@Configuration
public class PersistenceContext {
    private final Environment env;
    private final Properties properties = new Properties();

    public PersistenceContext(Environment env) {
        this.env = env;
        this.properties.putAll(((AbstractEnvironment) env).getPropertySources().stream()
                .filter(propertySource -> propertySource instanceof EnumerablePropertySource<?>)
                .flatMap(propertySource -> Arrays.stream(((EnumerablePropertySource<?>) propertySource).getPropertyNames()))
                .collect(Collectors.toMap(a -> a, env::getProperty)));
    }

    @Bean
    public GatewayFactory gatewayFactory() {
        var gateway = new MemoryGatewayFactoryImpl(properties);
        gateway.migrate();
        return gateway;
    }
}
