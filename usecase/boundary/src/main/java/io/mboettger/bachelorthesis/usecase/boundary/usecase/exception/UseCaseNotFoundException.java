package io.mboettger.bachelorthesis.usecase.boundary.usecase.exception;

public class UseCaseNotFoundException extends Exception{
    public UseCaseNotFoundException(String message) {
        super(message);
    }
}
