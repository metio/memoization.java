/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongFunction;
import java.util.function.LongToIntFunction;

import static java.util.Objects.requireNonNull;

final class LongToIntFunctionMemoizer<KEY>
        extends AbstractMemoizer<KEY, Integer>
        implements LongToIntFunction {

    private final LongFunction<KEY> keyFunction;
    private final LongToIntFunction function;

    LongToIntFunctionMemoizer(
            final ConcurrentMap<KEY, Integer> cache,
            final LongFunction<KEY> keyFunction,
            final LongToIntFunction function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL LongToIntFunction - provide an actual LongToIntFunction to fix this.");
    }

    @Override
    public int applyAsInt(final long value) {
        return computeIfAbsent(keyFunction.apply(value), key -> function.applyAsInt(value));
    }

}
