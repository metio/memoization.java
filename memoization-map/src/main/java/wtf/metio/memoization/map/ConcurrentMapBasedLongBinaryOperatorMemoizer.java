/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import wtf.metio.memoization.core.LongBinaryFunction;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongBinaryOperator;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedLongBinaryOperatorMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Long>
        implements LongBinaryOperator {

    private final LongBinaryFunction<KEY> keyFunction;
    private final LongBinaryOperator operator;

    public ConcurrentMapBasedLongBinaryOperatorMemoizer(
            final ConcurrentMap<KEY, Long> cache,
            final LongBinaryFunction<KEY> keyFunction,
            final LongBinaryOperator operator) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.longBinaryOperatorHashCodeKeyFunction()'.");
        this.operator = requireNonNull(operator,
                "Cannot memoize a NULL LongBinaryOperator - provide an actual LongBinaryOperator to fix this.");
    }

    @Override
    public long applyAsLong(final long left, final long right) {
        final KEY key = keyFunction.apply(left, right);
        return computeIfAbsent(key, givenKey -> operator.applyAsLong(left, right));
    }

}
