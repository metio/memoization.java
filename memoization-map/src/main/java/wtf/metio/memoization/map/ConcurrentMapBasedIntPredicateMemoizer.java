/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedIntPredicateMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Boolean>
        implements IntPredicate {

    private final IntFunction<KEY> keyFunction;
    private final IntPredicate predicate;

    ConcurrentMapBasedIntPredicateMemoizer(
            final ConcurrentMap<KEY, Boolean> cache,
            final IntFunction<KEY> keyFunction,
            final IntPredicate predicate) {
        super(cache);
        this.keyFunction = keyFunction;
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final int value) {
        final KEY key = keyFunction.apply(value);
        return computeIfAbsent(key, givenKey -> predicate.test(value));
    }

}
