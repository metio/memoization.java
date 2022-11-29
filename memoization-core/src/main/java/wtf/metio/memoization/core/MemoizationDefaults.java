/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.core;

import edu.umd.cs.findbugs.annotations.CheckReturnValue;

import java.util.Arrays;
import java.util.function.Supplier;

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
    @CheckReturnValue
    public static Supplier<Integer> staticKey() {
        return () -> 1;
    }

    /**
     * Generic cache key function that uses the hash codes of its given arguments to construct a cache key.
     *
     * @param values The values to use.
     * @return The constructed cache key.
     */
    @CheckReturnValue
    public static int hashCodes(final Object... values) {
        return Arrays.hashCode(values);
    }

}
