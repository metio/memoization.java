/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.LongFunction;
import java.util.function.LongUnaryOperator;

import javax.cache.Cache;

final class JCacheBasedLongUnaryOperatorMemoizer<KEY>
        extends AbstractJCacheBasedMemoizer<KEY, Long>
        implements LongUnaryOperator {

    private final LongFunction<KEY> keyFunction;
    private final LongUnaryOperator biFunction;

    JCacheBasedLongUnaryOperatorMemoizer(
            final Cache<KEY, Long> cache,
            final LongFunction<KEY> keyFunction,
            final LongUnaryOperator biFunction) {
        super(cache);
        this.keyFunction = keyFunction;
        this.biFunction = biFunction;
    }

    @Override
    public long applyAsLong(final long value) {
        final KEY key = keyFunction.apply(value);
        return invoke(key, givenKey -> Long.valueOf(biFunction.applyAsLong(value))).longValue();
    }

}
