/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.BiFunction;
import java.util.function.ToIntBiFunction;

final class GuavaCacheBasedToIntBiFunctionMemoizer<FIRST, SECOND, KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Integer>
        implements ToIntBiFunction<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final ToIntBiFunction<FIRST, SECOND> biFunction;

    GuavaCacheBasedToIntBiFunctionMemoizer(
            final Cache<KEY, Integer> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final ToIntBiFunction<FIRST, SECOND> biFunction) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biFunction = biFunction;
    }

    @Override
    public int applyAsInt(final FIRST first, final SECOND second) {
        final KEY key = keyFunction.apply(first, second);
        return get(key, givenKey -> biFunction.applyAsInt(first, second));
    }

}
