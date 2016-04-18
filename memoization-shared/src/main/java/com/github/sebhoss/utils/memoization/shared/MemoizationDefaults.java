package com.github.sebhoss.utils.memoization.shared;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 *
 */
public final class MemoizationDefaults {

    /**
     * @return The default key supplier used throughout the library.
     */
    public static Supplier<String> defaultSupplierKey() {
        return () -> "SUPPLIED"; //$NON-NLS-1$
    }

    /**
     * @return The default key function used throughout the library.
     */
    public static <T, U> BiFunction<T, U, String> hashCodeKeyFunction() {
        return (first, second) -> {
            final int firstHashCode = Objects.hashCode(first);
            final int secondHashCode = Objects.hashCode(second);
            return firstHashCode + " " + secondHashCode; //$NON-NLS-1$
        };
    }

    private MemoizationDefaults() {
        // utility class
    }

}
