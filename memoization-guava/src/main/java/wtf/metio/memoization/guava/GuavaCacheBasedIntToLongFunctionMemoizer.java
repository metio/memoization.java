/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;

final class GuavaCacheBasedIntToLongFunctionMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Long>
        implements IntToLongFunction {

    private final IntFunction<KEY> keyFunction;
    private final IntToLongFunction function;

    GuavaCacheBasedIntToLongFunctionMemoizer(
            final Cache<KEY, Long> cache,
            final IntFunction<KEY> keyFunction,
            final IntToLongFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public long applyAsLong(final int value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> function.applyAsLong(value));
    }

}
