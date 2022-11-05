/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedLongPredicateMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Boolean>
        implements LongPredicate {

    private final LongFunction<KEY> keyFunction;
    private final LongPredicate predicate;

    ConcurrentMapBasedLongPredicateMemoizer(
            final ConcurrentMap<KEY, Boolean> cache,
            final LongFunction<KEY> keyFunction,
            final LongPredicate predicate) {
        super(cache);
        this.keyFunction = keyFunction;
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final long value) {
        final KEY key = keyFunction.apply(value);
        return computeIfAbsent(key, givenKey -> predicate.test(value));
    }

}
