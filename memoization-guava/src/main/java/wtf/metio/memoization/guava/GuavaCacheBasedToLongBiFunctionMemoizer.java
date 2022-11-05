/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.BiFunction;
import java.util.function.ToLongBiFunction;

final class GuavaCacheBasedToLongBiFunctionMemoizer<FIRST, SECOND, KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Long>
        implements ToLongBiFunction<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final ToLongBiFunction<FIRST, SECOND> biFunction;

    GuavaCacheBasedToLongBiFunctionMemoizer(
            final Cache<KEY, Long> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final ToLongBiFunction<FIRST, SECOND> biFunction) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biFunction = biFunction;
    }

    @Override
    public long applyAsLong(final FIRST first, final SECOND second) {
        final KEY key = keyFunction.apply(first, second);
        return get(key, givenKey -> biFunction.applyAsLong(first, second));
    }

}
