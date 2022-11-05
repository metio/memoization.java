/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;

final class GuavaCacheBasedDoubleToIntFunctionMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Integer>
        implements DoubleToIntFunction {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleToIntFunction function;

    GuavaCacheBasedDoubleToIntFunctionMemoizer(
            final Cache<KEY, Integer> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoubleToIntFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public int applyAsInt(final double value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> function.applyAsInt(value));
    }

}
