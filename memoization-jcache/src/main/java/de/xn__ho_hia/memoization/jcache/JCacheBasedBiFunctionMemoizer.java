/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.BiFunction;

import javax.cache.Cache;

final class JCacheBasedBiFunctionMemoizer<FIRST, SECOND, KEY, OUTPUT>
        extends AbstractJCacheBasedMemoizer<KEY, OUTPUT>
        implements BiFunction<FIRST, SECOND, OUTPUT> {

    private final BiFunction<FIRST, SECOND, KEY>   keyFunction;
    private final BiFunction<FIRST, SECOND, OUTPUT> biFunction;

    JCacheBasedBiFunctionMemoizer(
            final Cache<KEY, OUTPUT> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiFunction<FIRST, SECOND, OUTPUT> biFunction) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biFunction = biFunction;
    }

    @Override
    public OUTPUT apply(final FIRST first, final SECOND second) {
        final KEY key = keyFunction.apply(first, second);
        return invoke(key, givenKey -> biFunction.apply(first, second));
    }

}
