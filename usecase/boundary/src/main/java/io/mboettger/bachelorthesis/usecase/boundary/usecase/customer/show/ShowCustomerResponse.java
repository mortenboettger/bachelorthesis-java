package io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show;

import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.UseCaseErrorMessageContainer;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.UseCaseUnknownErrorContainer;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.UseCaseValidationErrorContainer;
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model.CustomerModel;

import java.util.List;
import java.util.Map;

public class ShowCustomerResponse implements UseCaseResponse<ShowCustomerResponse.Error> {

    private final boolean success;

    public ShowCustomerResponse(boolean success) {
        this.success = success;
    }

    @Override
    public boolean isSuccessful() {
        return success;
    }

    public static class Success extends ShowCustomerResponse {
        private final CustomerModel customer;

        public Success(CustomerModel customer) {
            super(true);
            this.customer = customer;
        }

        public CustomerModel getCustomer() {
            return customer;
        }
    }

    public static class Error extends ShowCustomerResponse implements UseCaseErrorMessageContainer {
        private final String message;

        public Error(String message) {
            super(false);
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }

        public static class NotFound extends Error {
            public NotFound(String identifier) {
                super("Unable to find a customer by identifier " + identifier);
            }
        }

        public static class RequestValidationFailed extends Error implements UseCaseValidationErrorContainer {
            private final Map<String, List<String>> validationErrors;

            public RequestValidationFailed(Map<String, List<String>> validationErrors) {
                super("Invalid request to show a customer");
                this.validationErrors = validationErrors;
            }

            @Override
            public Map<String, List<String>> getValidationErrors() {
                return validationErrors;
            }
        }

        public static class Unknown extends Error implements UseCaseUnknownErrorContainer {
            private final Throwable error;

            public Unknown(Throwable error) {
                super("Error occurred while trying to find a customer");
                this.error = error;
            }

            @Override
            public Throwable getError() {
                return error;
            }
        }
    }
}
