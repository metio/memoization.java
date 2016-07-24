/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.Function;

import javax.cache.Cache;

final class JCacheBasedFunctionMemoizer<KEY, VALUE>
        extends AbstractJCacheBasedMemoizer<KEY, VALUE>
        implements Function<KEY, VALUE> {

    JCacheBasedFunctionMemoizer(final Cache<KEY, VALUE> cache) {
        super(cache);
    }

    @Override
    public VALUE apply(final KEY input) {
        return get(input);
    }

}
