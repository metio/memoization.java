/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;

import static java.util.Objects.requireNonNull;

final class LongPredicateMemoizer<KEY>
        extends AbstractMemoizer<KEY, Boolean>
        implements LongPredicate {

    private final LongFunction<KEY> keyFunction;
    private final LongPredicate predicate;

    LongPredicateMemoizer(
            final ConcurrentMap<KEY, Boolean> cache,
            final LongFunction<KEY> keyFunction,
            final LongPredicate predicate) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final long value) {
        return computeIfAbsent(keyFunction.apply(value), key -> predicate.test(value));
    }

}
