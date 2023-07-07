package io.mboettger.bachelorthesis.web.presenter;

import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show.ShowCustomerResponse;
import io.mboettger.bachelorthesis.web.converter.CustomerConverter;
import io.mboettger.bachelorthesis.web.model.CustomerResponseV1;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

public class ShowCustomerPresenter extends RestPresenter<CustomerResponseV1, ShowCustomerResponse, ShowCustomerResponse.Success, ShowCustomerResponse.Error> {

    private CustomerResponseV1 model;

    @Override
    public CustomerResponseV1 webResponse() {
        return model;
    }

    @Override
    public void success(ShowCustomerResponse.Success response) {
        model = CustomerConverter.toCustoemrResponseV1(response.getCustomer());
    }

    @Override
    public void failure(ShowCustomerResponse.Error response) {
        if (response instanceof ShowCustomerResponse.Error.NotFound error) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, error.getMessage());
        } else if (response instanceof ShowCustomerResponse.Error.RequestValidationFailed error) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, error.getValidationError());
        } else {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, response.getMessage());
        }
    }
}
