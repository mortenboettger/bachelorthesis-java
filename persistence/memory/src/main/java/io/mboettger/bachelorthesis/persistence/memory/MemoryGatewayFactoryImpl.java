package io.mboettger.bachelorthesis.persistence.memory;

import io.mboettger.bachelorthesis.persistence.gateway.CustomerGateway;
import io.mboettger.bachelorthesis.persistence.gateway.Gateway;
import io.mboettger.bachelorthesis.persistence.gateway.GatewayFactory;
import jakarta.persistence.Persistence;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Map;
import java.util.Properties;

public class MemoryGatewayFactoryImpl implements GatewayFactory {

    private final Flyway flyway;

    private final Map<Class<? extends Gateway>, ? extends Gateway> gateways;

    public MemoryGatewayFactoryImpl(Properties properties) {
        var entityManagerFactory = Persistence.createEntityManagerFactory(
                "bachelorthesis-persistence",
                properties
        );
        var entityManager = entityManagerFactory.createEntityManager();
        SessionFactory sessionFactory = entityManager.unwrap(Session.class).getSessionFactory();
        this.flyway = Flyway.configure().dataSource(
                properties.getProperty("hibernate.connection.url"),
                properties.getProperty("hibernate.connection.username"),
                properties.getProperty("hibernate.connection.password")
        ).baselineOnMigrate(true)
                .locations("classpath:/sql-migration")
                .load();
        this.gateways = Map.of(CustomerGateway.class, new CustomerGatewayImpl(sessionFactory));
    }

    @Override
    public <T extends Gateway> T make(Class<T> gateway) {
        var resolved = gateways.get(gateway);
        if (resolved != null) {
            //noinspection unchecked
            return (T) resolved;
        } else throw new IllegalArgumentException("Unable to find requested gateway " + gateway.getSimpleName() + " within this gatewayFactory");
    }

    @Override
    public void migrate() {
        flyway.migrate();
    }
}
