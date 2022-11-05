/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.Function;

final class GuavaCacheBasedFunctionMemoizer<INPUT, KEY, OUTPUT>
        extends AbstractGuavaCacheBasedMemoizer<KEY, OUTPUT>
        implements Function<INPUT, OUTPUT> {

    private final Function<INPUT, KEY> keyFunction;
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
