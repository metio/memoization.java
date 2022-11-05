/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.BiFunction;
import java.util.function.ToDoubleBiFunction;

final class GuavaCacheBasedToDoubleBiFunctionMemoizer<FIRST, SECOND, KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Double>
        implements ToDoubleBiFunction<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final ToDoubleBiFunction<FIRST, SECOND> biFunction;

    GuavaCacheBasedToDoubleBiFunctionMemoizer(
            final Cache<KEY, Double> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final ToDoubleBiFunction<FIRST, SECOND> biFunction) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biFunction = biFunction;
    }

    @Override
    public double applyAsDouble(final FIRST first, final SECOND second) {
        final KEY key = keyFunction.apply(first, second);
        return get(key, givenKey -> biFunction.applyAsDouble(first, second));
    }

}
