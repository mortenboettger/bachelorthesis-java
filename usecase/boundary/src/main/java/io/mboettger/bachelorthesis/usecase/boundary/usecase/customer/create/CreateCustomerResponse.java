package io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create;

import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.UseCaseErrorMessageContainer;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.UseCaseUnknownErrorContainer;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.UseCaseValidationErrorContainer;

import java.util.List;
import java.util.Map;

public abstract class CreateCustomerResponse implements UseCaseResponse<CreateCustomerResponse.Error> {

    private final boolean success;

    protected CreateCustomerResponse(boolean success) {
        this.success = success;
    }
    @Override
    public boolean isSuccessful() {
        return success;
    }

    public static final class Success extends CreateCustomerResponse {
        private final String id;

        public Success(String id) {
            super(true);
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    public abstract static class Error extends CreateCustomerResponse implements UseCaseErrorMessageContainer {
        private final String message;

        protected Error(String message) {
            super(false);
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }

        public static final class RequestValidationFailed extends Error implements UseCaseValidationErrorContainer {
            private final Map<String, List<String>> validationErrors;

            public RequestValidationFailed(Map<String, List<String>> validationErrors) {
                super("Invalid request to create a customer");
                this.validationErrors = validationErrors;
            }

            @Override
            public Map<String, List<String>> getValidationErrors() {
                return validationErrors;
            }
        }

        public static final class Unknown extends Error implements UseCaseUnknownErrorContainer {

            private final Throwable error;

            public Unknown(Throwable error) {
                super("Error occurred while trying to create a customer");
                this.error = error;
            }

            @Override
            public Throwable getError() {
                return error;
            }
        }
    }
}
