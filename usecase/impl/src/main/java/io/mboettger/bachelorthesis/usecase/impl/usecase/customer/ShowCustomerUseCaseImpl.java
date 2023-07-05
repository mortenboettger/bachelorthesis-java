package io.mboettger.bachelorthesis.usecase.impl.usecase.customer;

import io.mboettger.bachelorthesis.domain.customer.EmailAddress;
import io.mboettger.bachelorthesis.persistence.gateway.CustomerGateway;
import io.mboettger.bachelorthesis.usecase.boundary.OutputBoundary;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show.*;
import io.mboettger.bachelorthesis.usecase.impl.converter.CustomerConverter;

public class ShowCustomerUseCaseImpl implements ShowCustomerUseCase {

    private final CustomerGateway customerGateway;

    public ShowCustomerUseCaseImpl(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Override
    public void execute(ShowCustomerRequest request, OutputBoundary<ShowCustomerResponse, ShowCustomerResponse.Error> presenter) {
        // TODO: validation

        try {
            if (request instanceof ShowCustomerByIdRequest idRequest) {
                var customer = customerGateway.findOne(idRequest.customerId());

                if (customer != null) {
                    presenter.present(new ShowCustomerResponse.Success(CustomerConverter.toBoundary(customer)));
                } else {
                    presenter.present(new ShowCustomerResponse.Error.NotFound(idRequest.customerId()));
                }
            } else if (request instanceof ShowCustomerByEmailAddressRequest emailRequest) {
                var customer = customerGateway.findByEmail(new EmailAddress(emailRequest.emailAddress()));

                if (customer != null) {
                    presenter.present(new ShowCustomerResponse.Success(CustomerConverter.toBoundary(customer)));
                } else {
                    presenter.present(new ShowCustomerResponse.Error.NotFound(emailRequest.emailAddress()));
                }
            } else throw new IllegalStateException("Unknown request type " + request.getClass().getSimpleName());
        } catch (Exception e) {
            presenter.present(new ShowCustomerResponse.Error.Unknown(e));
        }
    }
}
