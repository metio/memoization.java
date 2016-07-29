/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.DoubleBinaryOperator;

import com.google.common.cache.Cache;

import de.xn__ho_hia.memoization.shared.DoubleBinaryFunction;

final class GuavaCacheBasedDoubleBinaryOperatorMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Double>
        implements DoubleBinaryOperator {

    private final DoubleBinaryFunction<KEY> keyFunction;
    private final DoubleBinaryOperator      function;

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
        return get(key, givenKey -> Double.valueOf(function.applyAsDouble(left, right))).doubleValue();
    }

}
