/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

import com.google.common.cache.Cache;

final class GuavaCacheBasedBiPredicateMemoizer<FIRST, SECOND, KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Boolean>
        implements BiPredicate<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final BiPredicate<FIRST, SECOND>     biPredicate;

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
        return get(key, givenKey -> Boolean.valueOf(biPredicate.test(first, second))).booleanValue();
    }

}
