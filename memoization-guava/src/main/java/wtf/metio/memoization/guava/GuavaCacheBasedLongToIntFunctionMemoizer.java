/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.LongFunction;
import java.util.function.LongToIntFunction;

final class GuavaCacheBasedLongToIntFunctionMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Integer>
        implements LongToIntFunction {

    private final LongFunction<KEY> keyFunction;
    private final LongToIntFunction function;

    GuavaCacheBasedLongToIntFunctionMemoizer(
            final Cache<KEY, Integer> cache,
            final LongFunction<KEY> keyFunction,
            final LongToIntFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public int applyAsInt(final long value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> function.applyAsInt(value));
    }

}
