/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.IntFunction;
import java.util.function.IntPredicate;

import com.google.common.cache.Cache;

final class GuavaCacheBasedIntPredicateMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Boolean>
        implements IntPredicate {

    private final IntFunction<KEY> keyFunction;
    private final IntPredicate     predicate;

    GuavaCacheBasedIntPredicateMemoizer(
            final Cache<KEY, Boolean> cache,
            final IntFunction<KEY> keyFunction,
            final IntPredicate predicate) {
        super(cache);
        this.keyFunction = keyFunction;
        this.predicate = predicate;
    }

    @Override
    public boolean test(final int input) {
        final KEY key = keyFunction.apply(input);
        return get(key, givenKey -> Boolean.valueOf(predicate.test(input))).booleanValue();
    }

}
