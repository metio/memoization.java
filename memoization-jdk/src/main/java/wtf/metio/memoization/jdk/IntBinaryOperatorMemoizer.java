/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntBinaryOperator;

import static java.util.Objects.requireNonNull;

final class IntBinaryOperatorMemoizer<KEY>
        extends AbstractMemoizer<KEY, Integer>
        implements IntBinaryOperator {

    private final IntBinaryFunction<KEY> keyFunction;
    private final IntBinaryOperator operator;

    IntBinaryOperatorMemoizer(
            final ConcurrentMap<KEY, Integer> cache,
            final IntBinaryFunction<KEY> keyFunction,
            final IntBinaryOperator operator) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.intBinaryOperatorHashCodeKeyFunction()'.");
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL IntBinaryOperator - provide an actual IntBinaryOperator to fix this.");
    }

    @Override
    public int applyAsInt(final int left, final int right) {
        return computeIfAbsent(keyFunction.apply(left, right), key -> operator.applyAsInt(left, right));
    }

}
