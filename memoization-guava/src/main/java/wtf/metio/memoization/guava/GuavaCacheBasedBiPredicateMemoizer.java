/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

final class GuavaCacheBasedBiPredicateMemoizer<FIRST, SECOND, KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Boolean>
        implements BiPredicate<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final BiPredicate<FIRST, SECOND> biPredicate;

    GuavaCacheBasedBiPredicateMemoizer(
            final Cache<KEY, Boolean> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiPredicate<FIRST, SECOND> biPredicate) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biPredicate = biPredicate;
    }

    @Override
    public boolean test(final FIRST first, final SECOND second) {
        final KEY key = keyFunction.apply(first, second);
        return get(key, givenKey -> biPredicate.test(first, second));
    }

}
