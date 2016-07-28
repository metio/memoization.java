/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.DoubleFunction;

import javax.cache.Cache;

final class JCacheBasedDoubleFunctionMemoizer<OUTPUT, KEY>
        extends AbstractJCacheBasedMemoizer<KEY, OUTPUT>
        implements DoubleFunction<OUTPUT> {

    private final DoubleFunction<KEY>    keyFunction;
    private final DoubleFunction<OUTPUT> function;

    JCacheBasedDoubleFunctionMemoizer(
            final Cache<KEY, OUTPUT> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoubleFunction<OUTPUT> function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public OUTPUT apply(final double value) {
        final KEY key = keyFunction.apply(value);
        return invoke(key, givenKey -> function.apply(value));
    }

}
