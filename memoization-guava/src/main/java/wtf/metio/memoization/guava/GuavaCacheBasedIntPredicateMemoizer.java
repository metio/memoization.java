/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.IntFunction;
import java.util.function.IntPredicate;

final class GuavaCacheBasedIntPredicateMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Boolean>
        implements IntPredicate {

    private final IntFunction<KEY> keyFunction;
    private final IntPredicate predicate;

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
        return get(key, givenKey -> predicate.test(input));
    }

}
