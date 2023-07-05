package io.mboettger.bachelorthesis.usecase.boundary.usecase;

import java.util.List;
import java.util.Map;

public interface UseCaseValidationErrorContainer {
    Map<String, List<String>> getValidationErrors();
}
