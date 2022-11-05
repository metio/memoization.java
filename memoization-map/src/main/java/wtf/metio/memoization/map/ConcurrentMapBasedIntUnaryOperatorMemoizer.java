/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedIntUnaryOperatorMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Integer>
        implements IntUnaryOperator {

    private final IntFunction<KEY> keyFunction;
    private final IntUnaryOperator operator;

    ConcurrentMapBasedIntUnaryOperatorMemoizer(
            final ConcurrentMap<KEY, Integer> cache,
            final IntFunction<KEY> keyFunction,
            final IntUnaryOperator operator) {
        super(cache);
        this.keyFunction = keyFunction;
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL IntUnaryOperator - provide an actual IntUnaryOperator to fix this.");
    }

    @Override
    public int applyAsInt(final int operand) {
        final KEY key = keyFunction.apply(operand);
        return computeIfAbsent(key, givenKey -> operator.applyAsInt(operand));
    }

}
