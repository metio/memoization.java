/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.LongBinaryOperator;

import com.google.common.cache.Cache;

import de.xn__ho_hia.memoization.shared.LongBinaryFunction;

final class GuavaCacheBasedLongBinaryOperatorMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Long>
        implements LongBinaryOperator {

    private final LongBinaryFunction<KEY> keyFunction;
    private final LongBinaryOperator      function;

    GuavaCacheBasedLongBinaryOperatorMemoizer(
            final Cache<KEY, Long> cache,
            final LongBinaryFunction<KEY> keyFunction,
            final LongBinaryOperator function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public long applyAsLong(final long left, final long right) {
        final KEY key = keyFunction.apply(left, right);
        return get(key, givenKey -> Long.valueOf(function.applyAsLong(left, right))).longValue();
    }

}
