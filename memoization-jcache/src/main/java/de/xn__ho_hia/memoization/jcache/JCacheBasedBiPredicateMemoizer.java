/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

import javax.cache.Cache;

final class JCacheBasedBiPredicateMemoizer<FIRST, SECOND, KEY>
        extends AbstractJCacheBasedMemoizer<KEY, Boolean>
        implements BiPredicate<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final BiPredicate<FIRST, SECOND>     biPredicate;

    JCacheBasedBiPredicateMemoizer(
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
        return invoke(key, givenKey -> Boolean.valueOf(biPredicate.test(first, second))).booleanValue();
    }

}
