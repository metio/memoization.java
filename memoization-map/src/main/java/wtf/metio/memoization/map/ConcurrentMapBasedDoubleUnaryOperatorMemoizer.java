/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedDoubleUnaryOperatorMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Double>
        implements DoubleUnaryOperator {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleUnaryOperator operator;

    ConcurrentMapBasedDoubleUnaryOperatorMemoizer(
            final ConcurrentMap<KEY, Double> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoubleUnaryOperator operator) {
        super(cache);
        this.keyFunction = keyFunction;
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL DoubleUnaryOperator - provide an actual DoubleUnaryOperator to fix this.");
    }

    @Override
    public double applyAsDouble(final double operand) {
        final KEY key = keyFunction.apply(operand);
        return computeIfAbsent(key, givenKey -> operator.applyAsDouble(operand));
    }

}
