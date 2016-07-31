/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.BiFunction;
import java.util.function.ToDoubleBiFunction;

import javax.cache.Cache;

final class JCacheBasedToDoubleBiFunctionMemoizer<FIRST, SECOND, KEY>
        extends AbstractJCacheBasedMemoizer<KEY, Double>
        implements ToDoubleBiFunction<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY>    keyFunction;
    private final ToDoubleBiFunction<FIRST, SECOND> biFunction;

    JCacheBasedToDoubleBiFunctionMemoizer(
            final Cache<KEY, Double> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final ToDoubleBiFunction<FIRST, SECOND> biFunction) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biFunction = biFunction;
    }

    @Override
    public double applyAsDouble(final FIRST first, final SECOND second) {
        final KEY key = keyFunction.apply(first, second);
        return invoke(key, givenKey -> Double.valueOf(biFunction.applyAsDouble(first, second))).doubleValue();
    }

}
