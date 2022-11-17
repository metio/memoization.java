/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleBinaryOperator;

import static java.util.Objects.requireNonNull;

final class DoubleBinaryOperatorMemoizer<KEY>
        extends AbstractMemoizer<KEY, Double>
        implements DoubleBinaryOperator {

    private final DoubleBinaryFunction<KEY> keyFunction;
    private final DoubleBinaryOperator operator;

    DoubleBinaryOperatorMemoizer(
            final ConcurrentMap<KEY, Double> cache,
            final DoubleBinaryFunction<KEY> keyFunction,
            final DoubleBinaryOperator operator) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.doubleBinaryOperatorHashCodeKeyFunction()'.");
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL DoubleBinaryOperator - provide an actual DoubleBinaryOperator to fix this.");
    }

    @Override
    public double applyAsDouble(final double left, final double right) {
        return computeIfAbsent(keyFunction.apply(left, right), key -> operator.applyAsDouble(left, right));
    }

}
