/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;

import com.google.common.cache.Cache;

final class GuavaCacheBasedLongToDoubleFunctionMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Double>
        implements LongToDoubleFunction {

    private final LongFunction<KEY>    keyFunction;
    private final LongToDoubleFunction function;

    GuavaCacheBasedLongToDoubleFunctionMemoizer(
            final Cache<KEY, Double> cache,
            final LongFunction<KEY> keyFunction,
            final LongToDoubleFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public double applyAsDouble(final long value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> Double.valueOf(function.applyAsDouble(value))).doubleValue();
    }

}
