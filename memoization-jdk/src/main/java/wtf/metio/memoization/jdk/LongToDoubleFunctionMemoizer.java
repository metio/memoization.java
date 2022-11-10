/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;

import static java.util.Objects.requireNonNull;

final class LongToDoubleFunctionMemoizer<KEY>
        extends AbstractMemoizer<KEY, Double>
        implements LongToDoubleFunction {

    private final LongFunction<KEY> keyFunction;
    private final LongToDoubleFunction function;

    LongToDoubleFunctionMemoizer(
            final ConcurrentMap<KEY, Double> cache,
            final LongFunction<KEY> keyFunction,
            final LongToDoubleFunction function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL LongToDoubleFunction - provide an actual LongToDoubleFunction to fix this.");
    }

    @Override
    public double applyAsDouble(final long value) {
        return computeIfAbsent(keyFunction.apply(value), key -> function.applyAsDouble(value));
    }

}
