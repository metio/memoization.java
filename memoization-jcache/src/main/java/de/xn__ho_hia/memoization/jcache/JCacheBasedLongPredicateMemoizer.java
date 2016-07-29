/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.LongFunction;
import java.util.function.LongPredicate;

import javax.cache.Cache;

final class JCacheBasedLongPredicateMemoizer<KEY>
        extends AbstractJCacheBasedMemoizer<KEY, Boolean>
        implements LongPredicate {

    private final LongFunction<KEY> keyFunction;
    private final LongPredicate     predicate;

    JCacheBasedLongPredicateMemoizer(
            final Cache<KEY, Boolean> cache,
            final LongFunction<KEY> keyFunction,
            final LongPredicate predicate) {
        super(cache);
        this.keyFunction = keyFunction;
        this.predicate = predicate;
    }

    @Override
    public boolean test(final long input) {
        final KEY key = keyFunction.apply(input);
        return invoke(key, givenKey -> Boolean.valueOf(predicate.test(input))).booleanValue();
    }

}
