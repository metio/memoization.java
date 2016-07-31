/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.Function;
import java.util.function.ToIntFunction;

import javax.cache.Cache;

final class JCacheBasedToIntFunctionMemoizer<FIRST, KEY>
        extends AbstractJCacheBasedMemoizer<KEY, Integer>
        implements ToIntFunction<FIRST> {

    private final Function<FIRST, KEY> keyFunction;
    private final ToIntFunction<FIRST> biFunction;

    JCacheBasedToIntFunctionMemoizer(
            final Cache<KEY, Integer> cache,
            final Function<FIRST, KEY> keyFunction,
            final ToIntFunction<FIRST> biFunction) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biFunction = biFunction;
    }

    @Override
    public int applyAsInt(final FIRST first) {
        final KEY key = keyFunction.apply(first);
        return invoke(key, givenKey -> Integer.valueOf(biFunction.applyAsInt(first))).intValue();
    }

}
