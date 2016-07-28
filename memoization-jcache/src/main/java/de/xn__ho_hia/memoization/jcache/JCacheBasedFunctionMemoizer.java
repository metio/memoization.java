/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.Function;

import javax.cache.Cache;

final class JCacheBasedFunctionMemoizer<INPUT, OUTPUT, KEY>
        extends AbstractJCacheBasedMemoizer<KEY, OUTPUT>
        implements Function<INPUT, OUTPUT> {

    private final Function<INPUT, KEY>    keyFunction;
    private final Function<INPUT, OUTPUT> function;

    JCacheBasedFunctionMemoizer(
            final Cache<KEY, OUTPUT> cache,
            final Function<INPUT, KEY> keyFunction,
            final Function<INPUT, OUTPUT> function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public OUTPUT apply(final INPUT input) {
        final KEY key = keyFunction.apply(input);
        return invoke(key, givenKey -> function.apply(input));
    }

}
