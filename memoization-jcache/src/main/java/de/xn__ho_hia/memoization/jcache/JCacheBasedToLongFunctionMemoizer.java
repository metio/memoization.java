/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.Function;
import java.util.function.ToLongFunction;

import javax.cache.Cache;

final class JCacheBasedToLongFunctionMemoizer<FIRST, KEY>
        extends AbstractJCacheBasedMemoizer<KEY, Long>
        implements ToLongFunction<FIRST> {

    private final Function<FIRST, KEY>  keyFunction;
    private final ToLongFunction<FIRST> biFunction;

    JCacheBasedToLongFunctionMemoizer(
            final Cache<KEY, Long> cache,
            final Function<FIRST, KEY> keyFunction,
            final ToLongFunction<FIRST> biFunction) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biFunction = biFunction;
    }

    @Override
    public long applyAsLong(final FIRST first) {
        final KEY key = keyFunction.apply(first);
        return invoke(key, givenKey -> Long.valueOf(biFunction.applyAsLong(first))).longValue();
    }

}
