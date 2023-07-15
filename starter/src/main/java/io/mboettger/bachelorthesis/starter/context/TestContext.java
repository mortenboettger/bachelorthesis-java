package io.mboettger.bachelorthesis.starter.context;

import io.mboettger.bachelorthesis.domain.customer.*;
import io.mboettger.bachelorthesis.domain.customer.address.*;
import io.mboettger.bachelorthesis.persistence.gateway.CustomerGateway;
import io.mboettger.bachelorthesis.persistence.gateway.GatewayFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {
    private Logger log = LoggerFactory.getLogger(TestContext.class);

    @Bean
    public CommandLineRunner testPersistence(GatewayFactory gatewayFactory) {
        return args -> {
            var customer = new Customer(
                    "",
                    new FirstName("Max"),
                    new LastName("Mustermann"),
                    new Address(
                            new Street("Musterstr."),
                            new HouseNumber("12"),
                            new HouseNumberAddition("A"),
                            new PostCode("12345"),
                            new City("Musterstadt"),
                            new District("Musterhausen")
                    ),
                    new PhoneNumber("12345678"),
                    new EmailAddress("a@b.cd")
            );
            var customerGateway = gatewayFactory.make(CustomerGateway.class);
            log.info("Saving {}", customer);
            var saved = customerGateway.save(customer);
            log.info("Saved {}", saved);
            log.info("Retrieved {}", customerGateway.findOne(saved.getId()));
        };
    }
}
