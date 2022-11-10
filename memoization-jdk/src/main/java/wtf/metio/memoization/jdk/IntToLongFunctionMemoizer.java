/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;

import static java.util.Objects.requireNonNull;

final class IntToLongFunctionMemoizer<KEY>
        extends AbstractMemoizer<KEY, Long>
        implements IntToLongFunction {

    private final IntFunction<KEY> keyFunction;
    private final IntToLongFunction function;

    IntToLongFunctionMemoizer(
            final ConcurrentMap<KEY, Long> cache,
            final IntFunction<KEY> keyFunction,
            final IntToLongFunction function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL IntToLongFunction - provide an actual IntToLongFunction to fix this.");
    }

    @Override
    public long applyAsLong(final int value) {
        return computeIfAbsent(keyFunction.apply(value), key -> function.applyAsLong(value));
    }

}
