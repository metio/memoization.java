/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;

import com.google.common.cache.Cache;

final class GuavaCacheBasedIntToLongFunctionMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Long>
        implements IntToLongFunction {

    private final IntFunction<KEY>  keyFunction;
    private final IntToLongFunction function;

    GuavaCacheBasedIntToLongFunctionMemoizer(
            final Cache<KEY, Long> cache,
            final IntFunction<KEY> keyFunction,
            final IntToLongFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public long applyAsLong(final int value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> Long.valueOf(function.applyAsLong(value))).longValue();
    }

}
