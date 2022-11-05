/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;
import wtf.metio.memoization.core.DoubleBinaryFunction;

import java.util.function.DoubleBinaryOperator;

final class GuavaCacheBasedDoubleBinaryOperatorMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Double>
        implements DoubleBinaryOperator {

    private final DoubleBinaryFunction<KEY> keyFunction;
    private final DoubleBinaryOperator function;

    GuavaCacheBasedDoubleBinaryOperatorMemoizer(
            final Cache<KEY, Double> cache,
            final DoubleBinaryFunction<KEY> keyFunction,
            final DoubleBinaryOperator function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public double applyAsDouble(final double left, final double right) {
        final KEY key = keyFunction.apply(left, right);
        return get(key, givenKey -> function.applyAsDouble(left, right));
    }

}
