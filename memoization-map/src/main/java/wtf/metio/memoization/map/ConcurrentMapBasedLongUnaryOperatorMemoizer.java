/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongFunction;
import java.util.function.LongUnaryOperator;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedLongUnaryOperatorMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Long>
        implements LongUnaryOperator {

    private final LongFunction<KEY> keyFunction;
    private final LongUnaryOperator operator;

    ConcurrentMapBasedLongUnaryOperatorMemoizer(
            final ConcurrentMap<KEY, Long> cache,
            final LongFunction<KEY> keyFunction,
            final LongUnaryOperator operator) {
        super(cache);
        this.keyFunction = keyFunction;
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL LongUnaryOperator - provide an actual LongUnaryOperator to fix this.");
    }

    @Override
    public long applyAsLong(final long operand) {
        final KEY key = keyFunction.apply(operand);
        return computeIfAbsent(key, givenKey -> operator.applyAsLong(operand)).intValue();
    }

}
