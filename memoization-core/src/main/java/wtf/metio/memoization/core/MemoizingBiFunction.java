/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.core;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @param <FIRST>  The type of the first input parameter.
 * @param <SECOND> The type of the second input parameter.
 * @param <KEY>    The type of the key to use for memoization.
 * @param <VALUE>  The type of the value to calculate.
 */
public interface MemoizingBiFunction<FIRST, SECOND, KEY, VALUE> extends BiFunction<FIRST, SECOND, VALUE> {

    /**
     * @return The function to calculate the key.
     */
    BiFunction<FIRST, SECOND, KEY> getKeyFunction();

    /**
     * @return The {@link BiFunction} that controls how to memoize the actual {@link BiFunction}.
     */
    BiFunction<KEY, Function<KEY, VALUE>, VALUE> getMemoizingFunction();

    /**
     * @return The {@link BiFunction} to wrap/memoize.
     */
    BiFunction<FIRST, SECOND, VALUE> getBiFunction();

    @Override
    default VALUE apply(final FIRST first, final SECOND second) {
        final KEY key = getKeyFunction().apply(first, second);
        final BiFunction<KEY, Function<KEY, VALUE>, VALUE> memoizer = Objects.requireNonNull(getMemoizingFunction());
        return memoizer.apply(key, givenKey -> getBiFunction().apply(first, second));
    }

}
