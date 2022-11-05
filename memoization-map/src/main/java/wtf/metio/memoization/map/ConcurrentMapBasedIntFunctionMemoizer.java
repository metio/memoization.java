/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedIntFunctionMemoizer<KEY, OUTPUT>
        extends ConcurrentMapBasedMemoizer<KEY, OUTPUT>
        implements IntFunction<OUTPUT> {

    private final IntFunction<KEY> keyFunction;
    private final IntFunction<OUTPUT> function;

    ConcurrentMapBasedIntFunctionMemoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final IntFunction<KEY> keyFunction,
            final IntFunction<OUTPUT> function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = requireNonNull(function,
                "Cannot memoize a NULL IntFunction - provide an actual IntFunction to fix this.");
    }

    @Override
    public OUTPUT apply(final int value) {
        final KEY key = keyFunction.apply(value);
        return computeIfAbsent(key, givenKey -> function.apply(value));
    }

}
