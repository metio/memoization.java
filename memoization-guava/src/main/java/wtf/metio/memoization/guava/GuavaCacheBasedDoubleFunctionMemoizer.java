/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.DoubleFunction;

final class GuavaCacheBasedDoubleFunctionMemoizer<KEY, OUTPUT>
        extends AbstractGuavaCacheBasedMemoizer<KEY, OUTPUT>
        implements DoubleFunction<OUTPUT> {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleFunction<OUTPUT> function;

    GuavaCacheBasedDoubleFunctionMemoizer(
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
        return get(key, givenKey -> function.apply(value));
    }

}
