/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;

import static java.util.Objects.requireNonNull;

final class IntPredicateMemoizer<KEY>
        extends AbstractMemoizer<KEY, Boolean>
        implements IntPredicate {

    private final IntFunction<KEY> keyFunction;
    private final IntPredicate predicate;

    IntPredicateMemoizer(
            final ConcurrentMap<KEY, Boolean> cache,
            final IntFunction<KEY> keyFunction,
            final IntPredicate predicate) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.predicate = requireNonNull(predicate,
                "Cannot memoize a NULL Predicate - provide an actual Predicate to fix this.");
    }

    @Override
    public boolean test(final int value) {
        return computeIfAbsent(keyFunction.apply(value), key -> predicate.test(value));
    }

}
