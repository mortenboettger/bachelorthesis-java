package io.mboettger.bachelorthesis.usecase.boundary;

public interface UseCaseResponse<E extends UseCaseResponse<E>> {
    boolean isSuccessful();
}
