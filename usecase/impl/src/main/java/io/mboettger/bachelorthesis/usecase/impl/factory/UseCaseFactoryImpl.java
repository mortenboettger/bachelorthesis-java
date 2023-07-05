package io.mboettger.bachelorthesis.usecase.impl.factory;

import io.mboettger.bachelorthesis.persistence.gateway.CustomerGateway;
import io.mboettger.bachelorthesis.persistence.gateway.GatewayFactory;
import io.mboettger.bachelorthesis.usecase.boundary.InputBoundary;
import io.mboettger.bachelorthesis.usecase.boundary.UseCaseFactory;
import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create.CreateCustomerUseCase;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show.ShowCustomerUseCase;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.exception.UseCaseNotFoundException;
import io.mboettger.bachelorthesis.usecase.impl.usecase.customer.CreateCustomerUseCaseImpl;
import io.mboettger.bachelorthesis.usecase.impl.usecase.customer.ShowCustomerUseCaseImpl;

import java.util.Map;
import java.util.function.Supplier;

public class UseCaseFactoryImpl implements UseCaseFactory {

    private final GatewayFactory gatewayFactory;

    private Map<Class<? extends InputBoundary<?, ?, ?>>, Supplier<? extends InputBoundary<?, ?, ?>>> useCases;

    public UseCaseFactoryImpl(GatewayFactory gatewayFactory) {
        this.gatewayFactory = gatewayFactory;

        useCases = Map.of(
                ShowCustomerUseCase.class, () -> new ShowCustomerUseCaseImpl(gatewayFactory.make(CustomerGateway.class)), // TODO specific gateways?
                CreateCustomerUseCase.class, () -> new CreateCustomerUseCaseImpl(gatewayFactory.make(CustomerGateway.class))
        );
    }

    @Override
    public <R, P extends UseCaseResponse<E>, Q extends InputBoundary<R, P, E>, E extends UseCaseResponse<E>> Q get(Class<Q> inputBoundary) throws UseCaseNotFoundException {
        var useCase = useCases.get(inputBoundary);

        if (useCase != null) {
            //noinspection unchecked
            return (Q) useCase.get();
        } else throw new UseCaseNotFoundException("Unable to find a useCase implementation by class " + inputBoundary.getSimpleName());
    }
}
