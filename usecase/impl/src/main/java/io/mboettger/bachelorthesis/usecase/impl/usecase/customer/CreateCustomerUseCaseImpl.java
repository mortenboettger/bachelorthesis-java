package io.mboettger.bachelorthesis.usecase.impl.usecase.customer;

import io.mboettger.bachelorthesis.domain.customer.*;
import io.mboettger.bachelorthesis.persistence.gateway.CustomerGateway;
import io.mboettger.bachelorthesis.usecase.boundary.OutputBoundary;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create.CreateCustomerRequest;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create.CreateCustomerResponse;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create.CreateCustomerUseCase;
import io.mboettger.bachelorthesis.usecase.impl.converter.AddressConverter;

public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public CreateCustomerUseCaseImpl(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Override
    public void execute(CreateCustomerRequest request, OutputBoundary<CreateCustomerResponse, CreateCustomerResponse.Error> presenter) {
        if (request.getEmailAddress().isPresent() && customerGateway.existsByEmail(new EmailAddress(request.getEmailAddress().get()))) {
            presenter.present(new CreateCustomerResponse.Error.RequestValidationFailed(request.getEmailAddress().get() + " already in use"));
            return;
        }

        if (request.getPhoneNumber().isPresent() && !request.getPhoneNumber().get().matches("^\\+?[1-9][0-9\\-]+$")) {
            presenter.present(new CreateCustomerResponse.Error.RequestValidationFailed("Malformed phone-number"));
            return;
        }

        try {
            var customer = new Customer(
                    null,
                    new FirstName(request.getFirstName()),
                    new LastName(request.getLastName()),
                    AddressConverter.toDomain(request.getAddress()),
                    request.getPhoneNumber().map(PhoneNumber::new).orElse(null),
                    request.getEmailAddress().map(EmailAddress::new).orElse(null)
            );

            var saved = customerGateway.save(customer);

            presenter.present(new CreateCustomerResponse.Success(saved.getId()));
        } catch (Exception e) {
            presenter.present(new CreateCustomerResponse.Error.Unknown(e));
        }
    }
}
