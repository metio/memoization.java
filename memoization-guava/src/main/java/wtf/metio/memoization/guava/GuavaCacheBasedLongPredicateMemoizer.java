/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.LongFunction;
import java.util.function.LongPredicate;

final class GuavaCacheBasedLongPredicateMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Boolean>
        implements LongPredicate {

    private final LongFunction<KEY> keyFunction;
    private final LongPredicate predicate;

    GuavaCacheBasedLongPredicateMemoizer(
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
        return get(key, givenKey -> predicate.test(input));
    }

}
