/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.core;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Defaults used throughout the library.
 */
public final class MemoizationDefaults {

    private MemoizationDefaults() {
        // utility class
    }

    /**
     * A cache key supplier that always returns the same cache key.
     *
     * @return The default key supplier used throughout the library.
     */
    public static Supplier<String> staticKey() {
        return () -> "SUPPLIED";
    }

    /**
     * Generic cache key function that uses the hash codes of its given arguments to construct a cache key.
     *
     * @param values The values to use.
     * @return The constructed cache key.
     */
    public static String hashCodes(final Object... values) {
        return Arrays.stream(values)
                .map(Object::hashCode)
                .map(Objects::toString)
                .collect(Collectors.joining(" "));
    }

}
