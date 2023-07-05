package io.mboettger.bachelorthesis.usecase.boundary;

import io.mboettger.bachelorthesis.usecase.boundary.usecase.exception.UseCaseNotFoundException;

public interface UseCaseFactory {
    <R, P extends UseCaseResponse<E>, Q extends InputBoundary<R, P, E>, E extends UseCaseResponse<E>> Q get(Class<Q> inputBoundary) throws UseCaseNotFoundException;
}
