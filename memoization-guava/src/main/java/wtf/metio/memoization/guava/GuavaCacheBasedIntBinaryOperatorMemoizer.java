/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;
import wtf.metio.memoization.core.IntBinaryFunction;

import java.util.function.IntBinaryOperator;

final class GuavaCacheBasedIntBinaryOperatorMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Integer>
        implements IntBinaryOperator {

    private final IntBinaryFunction<KEY> keyFunction;
    private final IntBinaryOperator function;

    GuavaCacheBasedIntBinaryOperatorMemoizer(
            final Cache<KEY, Integer> cache,
            final IntBinaryFunction<KEY> keyFunction,
            final IntBinaryOperator function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public int applyAsInt(final int left, final int right) {
        final KEY key = keyFunction.apply(left, right);
        return get(key, givenKey -> function.applyAsInt(left, right));
    }

}
