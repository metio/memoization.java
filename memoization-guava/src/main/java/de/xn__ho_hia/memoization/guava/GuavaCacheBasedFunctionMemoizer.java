/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.Function;

import com.google.common.cache.Cache;

final class GuavaCacheBasedFunctionMemoizer<INPUT, KEY, OUTPUT>
        extends AbstractGuavaCacheBasedMemoizer<KEY, OUTPUT>
        implements Function<INPUT, OUTPUT> {

    private final Function<INPUT, KEY>    keyFunction;
    private final Function<INPUT, OUTPUT> function;

    GuavaCacheBasedFunctionMemoizer(
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
        return get(key, givenKey -> function.apply(input));
    }

}
