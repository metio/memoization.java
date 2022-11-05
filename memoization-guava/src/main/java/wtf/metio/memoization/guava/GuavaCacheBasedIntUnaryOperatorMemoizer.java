/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

final class GuavaCacheBasedIntUnaryOperatorMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Integer>
        implements IntUnaryOperator {

    private final IntFunction<KEY> keyFunction;
    private final IntUnaryOperator function;

    GuavaCacheBasedIntUnaryOperatorMemoizer(
            final Cache<KEY, Integer> cache,
            final IntFunction<KEY> keyFunction,
            final IntUnaryOperator function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public int applyAsInt(final int value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> function.applyAsInt(value));
    }

}
