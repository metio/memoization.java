/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;

import javax.cache.Cache;

final class JCacheBasedIntToLongFunctionMemoizer<KEY>
        extends AbstractJCacheBasedMemoizer<KEY, Long>
        implements IntToLongFunction {

    private final IntFunction<KEY>  keyFunction;
    private final IntToLongFunction biFunction;

    JCacheBasedIntToLongFunctionMemoizer(
            final Cache<KEY, Long> cache,
            final IntFunction<KEY> keyFunction,
            final IntToLongFunction biFunction) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biFunction = biFunction;
    }

    @Override
    public long applyAsLong(final int value) {
        final KEY key = keyFunction.apply(value);
        return invoke(key, givenKey -> Long.valueOf(biFunction.applyAsLong(value))).longValue();
    }

}
