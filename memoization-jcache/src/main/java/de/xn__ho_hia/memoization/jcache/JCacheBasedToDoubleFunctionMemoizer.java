/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.Function;
import java.util.function.ToDoubleFunction;

import javax.cache.Cache;

final class JCacheBasedToDoubleFunctionMemoizer<FIRST, KEY>
        extends AbstractJCacheBasedMemoizer<KEY, Double>
        implements ToDoubleFunction<FIRST> {

    private final Function<FIRST, KEY>    keyFunction;
    private final ToDoubleFunction<FIRST> biFunction;

    JCacheBasedToDoubleFunctionMemoizer(
            final Cache<KEY, Double> cache,
            final Function<FIRST, KEY> keyFunction,
            final ToDoubleFunction<FIRST> biFunction) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biFunction = biFunction;
    }

    @Override
    public double applyAsDouble(final FIRST first) {
        final KEY key = keyFunction.apply(first);
        return invoke(key, givenKey -> Double.valueOf(biFunction.applyAsDouble(first))).doubleValue();
    }

}
