/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.Function;
import java.util.function.ToIntFunction;

import com.google.common.cache.Cache;

final class GuavaCacheBasedToIntFunctionMemoizer<INPUT, KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Integer>
        implements ToIntFunction<INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final ToIntFunction<INPUT> function;

    GuavaCacheBasedToIntFunctionMemoizer(
            final Cache<KEY, Integer> cache,
            final Function<INPUT, KEY> keyFunction,
            final ToIntFunction<INPUT> function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public int applyAsInt(final INPUT value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> Integer.valueOf(function.applyAsInt(value))).intValue();
    }

}
