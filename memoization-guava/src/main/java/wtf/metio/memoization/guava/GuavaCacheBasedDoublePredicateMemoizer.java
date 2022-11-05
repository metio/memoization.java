/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;

final class GuavaCacheBasedDoublePredicateMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Boolean>
        implements DoublePredicate {

    private final DoubleFunction<KEY> keyFunction;
    private final DoublePredicate predicate;

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
        return get(key, givenKey -> predicate.test(input));
    }

}
