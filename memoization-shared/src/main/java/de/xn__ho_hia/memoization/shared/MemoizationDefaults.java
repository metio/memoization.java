/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.shared;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Defaults used throughout the library.
 */
public final class MemoizationDefaults {

    private MemoizationDefaults() {
        // utility class
    }

    /**
     * @return The default key supplier used throughout the library.
     */
    public static Supplier<String> defaultKeySupplier() {
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

    /**
     * @return The default key function for {@link java.util.function.ObjDoubleConsumer}.
     */
    public static <VALUE> ObjDoubleFunction<VALUE, String> objDoubleConsumerHashCodeKeyFunction() {
        return (first, second) -> {
            final int firstHashCode = Objects.hashCode(first);
            final int secondHashCode = Double.valueOf(second).hashCode();
            return firstHashCode + " " + secondHashCode; //$NON-NLS-1$
        };
    }

    /**
     * @return The default key function for {@link java.util.function.ObjIntConsumer}.
     */
    public static <VALUE> ObjIntFunction<VALUE, String> objIntConsumerHashCodeKeyFunction() {
        return (first, second) -> {
            final int firstHashCode = Objects.hashCode(first);
            final int secondHashCode = Integer.valueOf(second).hashCode();
            return firstHashCode + " " + secondHashCode; //$NON-NLS-1$
        };
    }

    /**
     * @return The default key function for {@link java.util.function.ObjLongConsumer}.
     */
    public static <VALUE> ObjLongFunction<VALUE, String> objLongConsumerHashCodeKeyFunction() {
        return (first, second) -> {
            final int firstHashCode = Objects.hashCode(first);
            final int secondHashCode = Long.valueOf(second).hashCode();
            return firstHashCode + " " + secondHashCode; //$NON-NLS-1$
        };
    }

    /**
     * @return The default key function for {@link java.util.function.DoubleBinaryOperator}.
     */
    public static DoubleBinaryFunction<String> doubleBinaryOperatorHashCodeKeyFunction() {
        return (first, second) -> {
            final int firstHashCode = Double.valueOf(first).hashCode();
            final int secondHashCode = Double.valueOf(second).hashCode();
            return firstHashCode + " " + secondHashCode; //$NON-NLS-1$
        };
    }

    /**
     * @return The default key function for {@link java.util.function.IntBinaryOperator}.
     */
    public static IntBinaryFunction<String> intBinaryOperatorHashCodeKeyFunction() {
        return (first, second) -> {
            final int firstHashCode = Integer.valueOf(first).hashCode();
            final int secondHashCode = Integer.valueOf(second).hashCode();
            return firstHashCode + " " + secondHashCode; //$NON-NLS-1$
        };
    }

    /**
     * @return The default key function for {@link java.util.function.LongBinaryOperator}.
     */
    public static LongBinaryFunction<String> longBinaryOperatorHashCodeKeyFunction() {
        return (first, second) -> {
            final int firstHashCode = Long.valueOf(first).hashCode();
            final int secondHashCode = Long.valueOf(second).hashCode();
            return firstHashCode + " " + secondHashCode; //$NON-NLS-1$
        };
    }

}
