/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.IntFunction;

final class GuavaCacheBasedIntFunctionMemoizer<KEY, OUTPUT>
        extends AbstractGuavaCacheBasedMemoizer<KEY, OUTPUT>
        implements IntFunction<OUTPUT> {

    private final IntFunction<KEY> keyFunction;
    private final IntFunction<OUTPUT> function;

    GuavaCacheBasedIntFunctionMemoizer(
            final Cache<KEY, OUTPUT> cache,
            final IntFunction<KEY> keyFunction,
            final IntFunction<OUTPUT> function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = function;
    }

    @Override
    public OUTPUT apply(final int value) {
        final KEY key = keyFunction.apply(value);
        return get(key, givenKey -> function.apply(value));
    }

}
