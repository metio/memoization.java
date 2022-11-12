/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;

import static java.util.Objects.requireNonNull;

final class IntToDoubleFunctionMemoizer<KEY>
        extends AbstractMemoizer<KEY, Double>
        implements IntToDoubleFunction {

    private final IntFunction<KEY> keyFunction;
    private final IntToDoubleFunction function;

    IntToDoubleFunctionMemoizer(
            final ConcurrentMap<KEY, Double> cache,
            final IntFunction<KEY> keyFunction,
            final IntToDoubleFunction function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL IntToDoubleFunction - provide an actual IntToDoubleFunction to fix this.");
    }

    @Override
    public double applyAsDouble(final int value) {
        return computeIfAbsent(keyFunction.apply(value), key -> function.applyAsDouble(value));
    }

}
