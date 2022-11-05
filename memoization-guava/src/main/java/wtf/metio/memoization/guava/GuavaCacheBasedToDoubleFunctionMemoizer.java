/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.Function;
import java.util.function.ToDoubleFunction;

final class GuavaCacheBasedToDoubleFunctionMemoizer<INPUT, KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Double>
        implements ToDoubleFunction<INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final ToDoubleFunction<INPUT> function;

    GuavaCacheBasedToDoubleFunctionMemoizer(
            final Cache<KEY, Double> cache,
            final Function<INPUT, KEY> keyFunction,
            final ToDoubleFunction<INPUT> function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public double applyAsDouble(final INPUT value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> function.applyAsDouble(value));
    }

}
