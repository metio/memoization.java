/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;

final class GuavaCacheBasedIntToDoubleFunctionMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Double>
        implements IntToDoubleFunction {

    private final IntFunction<KEY> keyFunction;
    private final IntToDoubleFunction function;

    GuavaCacheBasedIntToDoubleFunctionMemoizer(
            final Cache<KEY, Double> cache,
            final IntFunction<KEY> keyFunction,
            final IntToDoubleFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public double applyAsDouble(final int value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> function.applyAsDouble(value));
    }

}
