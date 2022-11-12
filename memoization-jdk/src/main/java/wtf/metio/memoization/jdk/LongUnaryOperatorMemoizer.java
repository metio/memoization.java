/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongFunction;
import java.util.function.LongUnaryOperator;

import static java.util.Objects.requireNonNull;

final class LongUnaryOperatorMemoizer<KEY>
        extends AbstractMemoizer<KEY, Long>
        implements LongUnaryOperator {

    private final LongFunction<KEY> keyFunction;
    private final LongUnaryOperator operator;

    LongUnaryOperatorMemoizer(
            final ConcurrentMap<KEY, Long> cache,
            final LongFunction<KEY> keyFunction,
            final LongUnaryOperator operator) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'Long.valueOf()'.");
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL LongUnaryOperator - provide an actual LongUnaryOperator to fix this.");
    }

    @Override
    public long applyAsLong(final long operand) {
        return computeIfAbsent(keyFunction.apply(operand), key -> operator.applyAsLong(operand)).intValue();
    }

}
