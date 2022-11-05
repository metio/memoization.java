/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.DoubleFunction;
import java.util.function.DoubleToLongFunction;

final class GuavaCacheBasedDoubleToLongFunctionMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Long>
        implements DoubleToLongFunction {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleToLongFunction function;

    GuavaCacheBasedDoubleToLongFunctionMemoizer(
            final Cache<KEY, Long> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoubleToLongFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public long applyAsLong(final double value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> function.applyAsLong(value));
    }

}
