/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedDoublePredicateMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Boolean>
        implements DoublePredicate {

    private final DoubleFunction<KEY> keyFunction;
    private final DoublePredicate predicate;

    ConcurrentMapBasedDoublePredicateMemoizer(
            final ConcurrentMap<KEY, Boolean> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoublePredicate predicate) {
        super(cache);
        this.keyFunction = keyFunction;
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final double value) {
        final KEY key = keyFunction.apply(value);
        return computeIfAbsent(key, givenKey -> predicate.test(value));
    }

}
