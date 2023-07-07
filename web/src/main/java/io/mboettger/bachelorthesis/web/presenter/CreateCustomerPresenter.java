package io.mboettger.bachelorthesis.web.presenter;

import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create.CreateCustomerResponse;
import io.mboettger.bachelorthesis.web.model.CreateCustomerResponseV1;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.stream.Collectors;

public class CreateCustomerPresenter extends RestPresenter<CreateCustomerResponseV1, CreateCustomerResponse, CreateCustomerResponse.Success, CreateCustomerResponse.Error> {

    private CreateCustomerResponseV1 model;

    @Override
    public CreateCustomerResponseV1 webResponse() {
        return model;
    }

    @Override
    public void success(CreateCustomerResponse.Success response) {
        model = new CreateCustomerResponseV1(response.getId());
    }

    @Override
    public void failure(CreateCustomerResponse.Error response) {
        if (response instanceof CreateCustomerResponse.Error.RequestValidationFailed error) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, error.getValidationError());
        } else {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, response.getMessage());
        }
    }
}
