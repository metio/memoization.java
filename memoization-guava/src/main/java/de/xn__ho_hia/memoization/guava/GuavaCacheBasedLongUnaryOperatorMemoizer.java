/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.LongFunction;
import java.util.function.LongUnaryOperator;

import com.google.common.cache.Cache;

final class GuavaCacheBasedLongUnaryOperatorMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Long>
        implements LongUnaryOperator {

    private final LongFunction<KEY> keyFunction;
    private final LongUnaryOperator function;

    GuavaCacheBasedLongUnaryOperatorMemoizer(
            final Cache<KEY, Long> cache,
            final LongFunction<KEY> keyFunction,
            final LongUnaryOperator function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public long applyAsLong(final long operand) {
        final KEY key = keyFunction.apply(operand);
        return get(key, givenKey -> Long.valueOf(function.applyAsLong(operand))).longValue();
    }

}
