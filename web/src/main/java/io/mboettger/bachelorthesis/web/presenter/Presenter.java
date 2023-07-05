package io.mboettger.bachelorthesis.web.presenter;

import io.mboettger.bachelorthesis.usecase.boundary.OutputBoundary;
import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.UseCaseErrorMessageContainer;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.UseCaseUnknownErrorContainer;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public interface Presenter<Q, R extends UseCaseResponse<E>, S extends R, E extends R> extends OutputBoundary<R, E> {
    Q webResponse();
    void success(S response);

    void failure(E response);

    @Override
    default void present(R response) {
        if (response.isSuccessful()) {
            try {
                //noinspection unchecked
                success((S) response);
            } catch (ClassCastException e) {
                throw new IllegalStateException(
                        "The request was successfully handled internally but ${Presenter::class.simpleName} is unable to cast a useCase response of type " + response.getClass().getSimpleName() + " to the declared one. Concrete presenter generic parameters may be wrong",
                        e
                );
            }
        } else {
            if (response instanceof UseCaseUnknownErrorContainer errorContainer && response instanceof UseCaseErrorMessageContainer messageContainer && errorContainer.getError() instanceof ExecutionControl.NotImplementedException) { // TODO exception
                throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED, messageContainer.getMessage());
            }

            try {
                //noinspection unchecked
                failure((E) response);
            } catch (ClassCastException e) {
                throw new IllegalStateException(
                        "The request failed and the ${Presenter::class.simpleName} is unable to cast a useCase response of type " + response.getClass().getSimpleName() + " to the declared one. Concrete presenter generic parameters may be wrong",
                        e
                );
            }
        }
    }
}
