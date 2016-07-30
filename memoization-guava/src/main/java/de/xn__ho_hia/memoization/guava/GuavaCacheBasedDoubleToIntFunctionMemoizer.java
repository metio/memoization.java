/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;

import com.google.common.cache.Cache;

final class GuavaCacheBasedDoubleToIntFunctionMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Integer>
        implements DoubleToIntFunction {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleToIntFunction function;

    GuavaCacheBasedDoubleToIntFunctionMemoizer(
            final Cache<KEY, Integer> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoubleToIntFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public int applyAsInt(final double value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> Integer.valueOf(function.applyAsInt(value))).intValue();
    }

}
