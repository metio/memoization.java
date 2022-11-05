/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;
import wtf.metio.memoization.core.LongBinaryFunction;

import java.util.function.LongBinaryOperator;

final class GuavaCacheBasedLongBinaryOperatorMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Long>
        implements LongBinaryOperator {

    private final LongBinaryFunction<KEY> keyFunction;
    private final LongBinaryOperator function;

    GuavaCacheBasedLongBinaryOperatorMemoizer(
            final Cache<KEY, Long> cache,
            final LongBinaryFunction<KEY> keyFunction,
            final LongBinaryOperator function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public long applyAsLong(final long left, final long right) {
        final KEY key = keyFunction.apply(left, right);
        return get(key, givenKey -> function.applyAsLong(left, right));
    }

}
