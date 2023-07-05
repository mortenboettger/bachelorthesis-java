package io.mboettger.bachelorthesis.web.controller;

import io.mboettger.bachelorthesis.usecase.boundary.UseCaseFactory;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create.CreateCustomerUseCase;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show.ShowCustomerByEmailAddressRequest;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show.ShowCustomerByIdRequest;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show.ShowCustomerUseCase;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.exception.UseCaseNotFoundException;
import io.mboettger.bachelorthesis.web.converter.CustomerConverter;
import io.mboettger.bachelorthesis.web.model.CreateCustomerRequestV1;
import io.mboettger.bachelorthesis.web.model.CreateCustomerResponseV1;
import io.mboettger.bachelorthesis.web.model.CustomerResponseV1;
import io.mboettger.bachelorthesis.web.presenter.CreateCustomerPresenter;
import io.mboettger.bachelorthesis.web.presenter.ShowCustomerPresenter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final UseCaseFactory useCaseFactory;

    public CustomerController(UseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }

    @GetMapping("{id}")
    public CustomerResponseV1 getById(@PathVariable String id) throws UseCaseNotFoundException { // TODO
        var presenter = new ShowCustomerPresenter();
        useCaseFactory.get(ShowCustomerUseCase.class).execute(new ShowCustomerByIdRequest(id), presenter);
        return presenter.webResponse();
    }

    @GetMapping("/by-email/{email}")
    public CustomerResponseV1 getByEmail(@PathVariable String email) throws UseCaseNotFoundException { // TODO
        var presenter = new ShowCustomerPresenter();
        useCaseFactory.get(ShowCustomerUseCase.class).execute(new ShowCustomerByEmailAddressRequest(email), presenter);
        return presenter.webResponse();
    }

    @PostMapping
    public CreateCustomerResponseV1 create(@RequestBody CreateCustomerRequestV1 request) throws UseCaseNotFoundException { // TODO
        var presenter = new CreateCustomerPresenter();
        useCaseFactory.get(CreateCustomerUseCase.class).execute(CustomerConverter.toCreateCustomerRequest(request), presenter);
        return presenter.webResponse();
    }
}
