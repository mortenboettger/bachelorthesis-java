package io.mboettger.bachelorthesis.usecase.boundary;

@FunctionalInterface
public interface OutputBoundary<R extends UseCaseResponse<E>, E extends UseCaseResponse<E>> {
    void present(R response);
}
