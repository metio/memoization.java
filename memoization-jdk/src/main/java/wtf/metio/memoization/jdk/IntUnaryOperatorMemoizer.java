/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

import static java.util.Objects.requireNonNull;

final class IntUnaryOperatorMemoizer<KEY>
        extends AbstractMemoizer<KEY, Integer>
        implements IntUnaryOperator {

    private final IntFunction<KEY> keyFunction;
    private final IntUnaryOperator operator;

    IntUnaryOperatorMemoizer(
            final ConcurrentMap<KEY, Integer> cache,
            final IntFunction<KEY> keyFunction,
            final IntUnaryOperator operator) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL IntUnaryOperator - provide an actual IntUnaryOperator to fix this.");
    }

    @Override
    public int applyAsInt(final int operand) {
        return computeIfAbsent(keyFunction.apply(operand), key -> operator.applyAsInt(operand));
    }

}
