/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.Function;
import java.util.function.ToLongFunction;

final class GuavaCacheBasedToLongFunctionMemoizer<INPUT, KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Long>
        implements ToLongFunction<INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final ToLongFunction<INPUT> function;

    GuavaCacheBasedToLongFunctionMemoizer(
            final Cache<KEY, Long> cache,
            final Function<INPUT, KEY> keyFunction,
            final ToLongFunction<INPUT> function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public long applyAsLong(final INPUT value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> function.applyAsLong(value));
    }

}
