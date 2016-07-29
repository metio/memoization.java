/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.IntBinaryOperator;

import javax.cache.Cache;

import de.xn__ho_hia.memoization.shared.IntBinaryFunction;

final class JCacheBasedIntBinaryOperatorMemoizer<KEY>
        extends AbstractJCacheBasedMemoizer<KEY, Integer>
        implements IntBinaryOperator {

    private final IntBinaryFunction<KEY> keyFunction;
    private final IntBinaryOperator      function;

    JCacheBasedIntBinaryOperatorMemoizer(
            final Cache<KEY, Integer> cache,
            final IntBinaryFunction<KEY> keyFunction,
            final IntBinaryOperator function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public int applyAsInt(final int left, final int right) {
        final KEY key = keyFunction.apply(left, right);
        return invoke(key, givenKey -> Integer.valueOf(function.applyAsInt(left, right))).intValue();
    }

}
