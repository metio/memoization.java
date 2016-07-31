/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.BiFunction;
import java.util.function.ToLongBiFunction;

import javax.cache.Cache;

final class JCacheBasedToLongBiFunctionMemoizer<FIRST, SECOND, KEY>
        extends AbstractJCacheBasedMemoizer<KEY, Long>
        implements ToLongBiFunction<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY>  keyFunction;
    private final ToLongBiFunction<FIRST, SECOND> biFunction;

    JCacheBasedToLongBiFunctionMemoizer(
            final Cache<KEY, Long> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final ToLongBiFunction<FIRST, SECOND> biFunction) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biFunction = biFunction;
    }

    @Override
    public long applyAsLong(final FIRST first, final SECOND second) {
        final KEY key = keyFunction.apply(first, second);
        return invoke(key, givenKey -> Long.valueOf(biFunction.applyAsLong(first, second))).longValue();
    }

}
