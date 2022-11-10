/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;

import static java.util.Objects.requireNonNull;

final class DoublePredicateMemoizer<KEY>
        extends AbstractMemoizer<KEY, Boolean>
        implements DoublePredicate {

    private final DoubleFunction<KEY> keyFunction;
    private final DoublePredicate predicate;

    DoublePredicateMemoizer(
            final ConcurrentMap<KEY, Boolean> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoublePredicate predicate) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final double value) {
        return computeIfAbsent(keyFunction.apply(value), key -> predicate.test(value));
    }

}
