/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

import com.google.common.cache.Cache;

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
        return get(key, givenKey -> Double.valueOf(function.applyAsDouble(operand))).doubleValue();
    }

}
