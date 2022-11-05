/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

final class GuavaCacheBasedDoubleUnaryOperatorMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Double>
        implements DoubleUnaryOperator {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleUnaryOperator function;

    GuavaCacheBasedDoubleUnaryOperatorMemoizer(
            final Cache<KEY, Double> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoubleUnaryOperator function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public double applyAsDouble(final double operand) {
        final KEY key = keyFunction.apply(operand);
        return get(key, givenKey -> function.applyAsDouble(operand));
    }

}
