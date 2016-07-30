/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;

import com.google.common.cache.Cache;

final class GuavaCacheBasedIntToDoubleFunctionMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Double>
        implements IntToDoubleFunction {

    private final IntFunction<KEY>    keyFunction;
    private final IntToDoubleFunction function;

    GuavaCacheBasedIntToDoubleFunctionMemoizer(
            final Cache<KEY, Double> cache,
            final IntFunction<KEY> keyFunction,
            final IntToDoubleFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public double applyAsDouble(final int value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> Double.valueOf(function.applyAsDouble(value))).doubleValue();
    }

}
