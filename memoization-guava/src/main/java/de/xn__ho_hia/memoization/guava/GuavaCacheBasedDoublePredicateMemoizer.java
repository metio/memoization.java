/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;

import com.google.common.cache.Cache;

final class GuavaCacheBasedDoublePredicateMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Boolean>
        implements DoublePredicate {

    private final DoubleFunction<KEY> keyFunction;
    private final DoublePredicate     predicate;

    GuavaCacheBasedDoublePredicateMemoizer(
            final Cache<KEY, Boolean> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoublePredicate predicate) {
        super(cache);
        this.keyFunction = keyFunction;
        this.predicate = predicate;
    }

    @Override
    public boolean test(final double input) {
        final KEY key = keyFunction.apply(input);
        return get(key, givenKey -> Boolean.valueOf(predicate.test(input))).booleanValue();
    }

}
