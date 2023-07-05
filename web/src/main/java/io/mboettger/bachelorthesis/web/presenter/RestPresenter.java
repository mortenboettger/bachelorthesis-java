package io.mboettger.bachelorthesis.web.presenter;

import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class RestPresenter<Q, R extends UseCaseResponse<E>, S extends  R, E extends R> implements Presenter<Q, R, S, E> {
    protected void setResponseStatus(HttpStatus status) {
        var attributes = RequestContextHolder.getRequestAttributes();
        if (attributes instanceof ServletRequestAttributes servletRequestAttributes) {
            var response = servletRequestAttributes.getResponse();
            if (response != null) {
                response.setStatus(status.value());
            }
        }
    }
}
