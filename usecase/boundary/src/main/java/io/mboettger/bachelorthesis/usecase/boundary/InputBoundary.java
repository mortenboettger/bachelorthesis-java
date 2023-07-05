package io.mboettger.bachelorthesis.usecase.boundary;

@FunctionalInterface
public interface InputBoundary<R, P extends UseCaseResponse<E>, E extends UseCaseResponse<E>> {
    void execute(R request, OutputBoundary<P, E> presenter);
}
