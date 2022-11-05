/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.LongFunction;

final class GuavaCacheBasedLongFunctionMemoizer<KEY, OUTPUT>
        extends AbstractGuavaCacheBasedMemoizer<KEY, OUTPUT>
        implements LongFunction<OUTPUT> {

    private final LongFunction<KEY> keyFunction;
    private final LongFunction<OUTPUT> function;

    GuavaCacheBasedLongFunctionMemoizer(
            final Cache<KEY, OUTPUT> cache,
            final LongFunction<KEY> keyFunction,
            final LongFunction<OUTPUT> function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public OUTPUT apply(final long value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> function.apply(value));
    }

}
