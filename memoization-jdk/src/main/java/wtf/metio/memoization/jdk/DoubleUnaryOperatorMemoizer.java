/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

import static java.util.Objects.requireNonNull;

final class DoubleUnaryOperatorMemoizer<KEY>
        extends AbstractMemoizer<KEY, Double>
        implements DoubleUnaryOperator {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleUnaryOperator operator;

    DoubleUnaryOperatorMemoizer(
            final ConcurrentMap<KEY, Double> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoubleUnaryOperator operator) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL DoubleUnaryOperator - provide an actual DoubleUnaryOperator to fix this.");
    }

    @Override
    public double applyAsDouble(final double operand) {
        return computeIfAbsent(keyFunction.apply(operand), key -> operator.applyAsDouble(operand));
    }

}
