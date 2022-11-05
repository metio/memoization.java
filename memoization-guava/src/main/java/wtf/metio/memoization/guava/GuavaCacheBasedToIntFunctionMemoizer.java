/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.Function;
import java.util.function.ToIntFunction;

final class GuavaCacheBasedToIntFunctionMemoizer<INPUT, KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Integer>
        implements ToIntFunction<INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final ToIntFunction<INPUT> function;

    GuavaCacheBasedToIntFunctionMemoizer(
            final Cache<KEY, Integer> cache,
            final Function<INPUT, KEY> keyFunction,
            final ToIntFunction<INPUT> function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public int applyAsInt(final INPUT value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> function.applyAsInt(value));
    }

}
