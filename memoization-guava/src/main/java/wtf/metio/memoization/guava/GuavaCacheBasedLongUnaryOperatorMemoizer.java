/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.LongFunction;
import java.util.function.LongUnaryOperator;

final class GuavaCacheBasedLongUnaryOperatorMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Long>
        implements LongUnaryOperator {

    private final LongFunction<KEY> keyFunction;
    private final LongUnaryOperator function;

    GuavaCacheBasedLongUnaryOperatorMemoizer(
            final Cache<KEY, Long> cache,
            final LongFunction<KEY> keyFunction,
            final LongUnaryOperator function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public long applyAsLong(final long operand) {
        final KEY key = keyFunction.apply(operand);
        return get(key, givenKey -> function.applyAsLong(operand));
    }

}
