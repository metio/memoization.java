/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedIntToLongFunctionMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Long>
        implements IntToLongFunction {

    private final IntFunction<KEY> keyFunction;
    private final IntToLongFunction function;

    ConcurrentMapBasedIntToLongFunctionMemoizer(
            final ConcurrentMap<KEY, Long> cache,
            final IntFunction<KEY> keyFunction,
            final IntToLongFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = requireNonNull(function,
                "Cannot memoize a NULL IntToLongFunction - provide an actual IntToLongFunction to fix this.");
    }

    @Override
    public long applyAsLong(final int value) {
        final KEY key = keyFunction.apply(value);
        return computeIfAbsent(key, givenKey -> function.applyAsLong(value));
    }

}
