package io.mboettger.bachelorthesis.persistence.memory.helper;

import java.util.Arrays;
import java.util.Objects;

public final class PersistenceHelper {
    private PersistenceHelper() { }

    public static void throwIfNull(Object ...objects) {
        if (Arrays.stream(objects).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("should not be null");
        }
    }
}
