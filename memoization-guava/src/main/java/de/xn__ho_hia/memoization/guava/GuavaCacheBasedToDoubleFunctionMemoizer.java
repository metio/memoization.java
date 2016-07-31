/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.Function;
import java.util.function.ToDoubleFunction;

import com.google.common.cache.Cache;

final class GuavaCacheBasedToDoubleFunctionMemoizer<INPUT, KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Double>
        implements ToDoubleFunction<INPUT> {

    private final Function<INPUT, KEY>    keyFunction;
    private final ToDoubleFunction<INPUT> function;

    GuavaCacheBasedToDoubleFunctionMemoizer(
            final Cache<KEY, Double> cache,
            final Function<INPUT, KEY> keyFunction,
            final ToDoubleFunction<INPUT> function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public double applyAsDouble(final INPUT value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> Double.valueOf(function.applyAsDouble(value))).doubleValue();
    }

}
