/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;

import javax.cache.Cache;

final class JCacheBasedLongToDoubleFunctionMemoizer<KEY>
        extends AbstractJCacheBasedMemoizer<KEY, Double>
        implements LongToDoubleFunction {

    private final LongFunction<KEY>    keyFunction;
    private final LongToDoubleFunction biFunction;

    JCacheBasedLongToDoubleFunctionMemoizer(
            final Cache<KEY, Double> cache,
            final LongFunction<KEY> keyFunction,
            final LongToDoubleFunction biFunction) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biFunction = biFunction;
    }

    @Override
    public double applyAsDouble(final long value) {
        final KEY key = keyFunction.apply(value);
        return invoke(key, givenKey -> Double.valueOf(biFunction.applyAsDouble(value))).doubleValue();
    }

}
