/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.Function;

import com.google.common.cache.Cache;

final class GuavaCacheBasedFunctionMemoizer<KEY, VALUE>
        extends AbstractGuavaCacheBasedMemoizer<KEY, VALUE>
        implements Function<KEY, VALUE> {

    private final Function<KEY, VALUE> function;

    GuavaCacheBasedFunctionMemoizer(
            final Cache<KEY, VALUE> cache,
            final Function<KEY, VALUE> function) {
        super(cache);
        this.function = function;
    }

    @Override
    public VALUE apply(final KEY key) {
        return get(key, function);
    }

}
