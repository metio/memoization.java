/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.BiFunction;

final class GuavaCacheBasedBiFunctionMemoizer<FIRST, SECOND, KEY, OUTPUT>
        extends AbstractGuavaCacheBasedMemoizer<KEY, OUTPUT>
        implements BiFunction<FIRST, SECOND, OUTPUT> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final BiFunction<FIRST, SECOND, OUTPUT> biFunction;

    GuavaCacheBasedBiFunctionMemoizer(
            final Cache<KEY, OUTPUT> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biFunction = biFunction;
    }

    @Override
    public OUTPUT apply(final FIRST first, final SECOND second) {
        final KEY key = keyFunction.apply(first, second);
        return get(key, givenKey -> biFunction.apply(first, second));
    }

}
