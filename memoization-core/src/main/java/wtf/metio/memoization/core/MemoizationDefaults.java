/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.core;

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
        return () -> "SUPPLIED";
    }

    /**
     * @param <T> the type of the first argument to the function
     * @param <U> the type of the second argument to the function
     * @return The default key function used throughout the library.
     */
    public static <T, U> BiFunction<T, U, String> hashCodeKeyFunction() {
        return (first, second) -> {
            final int firstHashCode = Objects.hashCode(first);
            final int secondHashCode = Objects.hashCode(second);
            return firstHashCode + " " + secondHashCode;
        };
    }

    /**
     * @param <INPUT> the type of the first argument to the function
     * @return The default key function for {@link java.util.function.ObjDoubleConsumer}.
     */
    public static <INPUT> ObjDoubleFunction<INPUT, String> objDoubleConsumerHashCodeKeyFunction() {
        return (first, second) -> {
            final int firstHashCode = Objects.hashCode(first);
            final int secondHashCode = Double.valueOf(second).hashCode();
            return firstHashCode + " " + secondHashCode;
        };
    }

    /**
     * @param <INPUT> the type of the first argument to the function
     * @return The default key function for {@link java.util.function.ObjIntConsumer}.
     */
    public static <INPUT> ObjIntFunction<INPUT, String> objIntConsumerHashCodeKeyFunction() {
        return (first, second) -> {
            final int firstHashCode = Objects.hashCode(first);
            final int secondHashCode = Integer.valueOf(second).hashCode();
            return firstHashCode + " " + secondHashCode;
        };
    }

    /**
     * @param <INPUT> the type of the first argument to the function
     * @return The default key function for {@link java.util.function.ObjLongConsumer}.
     */
    public static <INPUT> ObjLongFunction<INPUT, String> objLongConsumerHashCodeKeyFunction() {
        return (first, second) -> {
            final int firstHashCode = Objects.hashCode(first);
            final int secondHashCode = Long.valueOf(second).hashCode();
            return firstHashCode + " " + secondHashCode;
        };
    }

    /**
     * @return The default key function for {@link java.util.function.DoubleBinaryOperator}.
     */
    public static DoubleBinaryFunction<String> doubleBinaryOperatorHashCodeKeyFunction() {
        return (first, second) -> {
            final int firstHashCode = Double.valueOf(first).hashCode();
            final int secondHashCode = Double.valueOf(second).hashCode();
            return firstHashCode + " " + secondHashCode;
        };
    }

    /**
     * @return The default key function for {@link java.util.function.IntBinaryOperator}.
     */
    public static IntBinaryFunction<String> intBinaryOperatorHashCodeKeyFunction() {
        return (first, second) -> {
            final int firstHashCode = Integer.valueOf(first).hashCode();
            final int secondHashCode = Integer.valueOf(second).hashCode();
            return firstHashCode + " " + secondHashCode;
        };
    }

    /**
     * @return The default key function for {@link java.util.function.LongBinaryOperator}.
     */
    public static LongBinaryFunction<String> longBinaryOperatorHashCodeKeyFunction() {
        return (first, second) -> {
            final int firstHashCode = Long.valueOf(first).hashCode();
            final int secondHashCode = Long.valueOf(second).hashCode();
            return firstHashCode + " " + secondHashCode;
        };
    }

}
