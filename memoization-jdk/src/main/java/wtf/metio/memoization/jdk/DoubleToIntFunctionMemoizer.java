/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;

import static java.util.Objects.requireNonNull;

final class DoubleToIntFunctionMemoizer<KEY>
        extends AbstractMemoizer<KEY, Integer>
        implements DoubleToIntFunction {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleToIntFunction function;

    DoubleToIntFunctionMemoizer(
            final ConcurrentMap<KEY, Integer> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoubleToIntFunction function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL DoubleToIntFunction - provide an actual DoubleToIntFunction to fix this.");
    }

    @Override
    public int applyAsInt(final double value) {
        return computeIfAbsent(keyFunction.apply(value), key -> function.applyAsInt(value));
    }

}
