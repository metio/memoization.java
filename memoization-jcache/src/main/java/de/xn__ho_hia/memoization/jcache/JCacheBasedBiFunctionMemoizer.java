/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.BiFunction;

import javax.cache.Cache;

final class JCacheBasedBiFunctionMemoizer<FIRST, SECOND, KEY, VALUE>
        extends AbstractJCacheBasedMemoizer<KEY, VALUE>
        implements BiFunction<FIRST, SECOND, VALUE> {

    private final BiFunction<FIRST, SECOND, KEY>   keyFunction;
    private final BiFunction<FIRST, SECOND, VALUE> biFunction;

    JCacheBasedBiFunctionMemoizer(
            final Cache<KEY, VALUE> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiFunction<FIRST, SECOND, VALUE> biFunction) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biFunction = biFunction;
    }

    @Override
    public VALUE apply(final FIRST t, final SECOND u) {
        final KEY key = keyFunction.apply(t, u);
        return invoke(key, givenKey -> biFunction.apply(t, u));
    }

}
