/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;

final class GuavaCacheBasedLongToDoubleFunctionMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Double>
        implements LongToDoubleFunction {

    private final LongFunction<KEY> keyFunction;
    private final LongToDoubleFunction function;

    GuavaCacheBasedLongToDoubleFunctionMemoizer(
            final Cache<KEY, Double> cache,
            final LongFunction<KEY> keyFunction,
            final LongToDoubleFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public double applyAsDouble(final long value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> function.applyAsDouble(value));
    }

}
