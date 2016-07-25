/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.Predicate;

import com.google.common.cache.Cache;

final class GuavaCacheBasedPredicateMemoizer<VALUE>
        extends AbstractGuavaCacheBasedMemoizer<VALUE, Boolean>
        implements Predicate<VALUE> {

    private final Predicate<VALUE> predicate;

    GuavaCacheBasedPredicateMemoizer(
            final Cache<VALUE, Boolean> cache,
            final Predicate<VALUE> predicate) {
        super(cache);
        this.predicate = predicate;
    }

    @Override
    public boolean test(final VALUE value) {
        return get(value, givenValue -> Boolean.valueOf(predicate.test(givenValue))).booleanValue();
    }

}
