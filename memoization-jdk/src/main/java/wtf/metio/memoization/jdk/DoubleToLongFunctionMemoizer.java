/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToLongFunction;

import static java.util.Objects.requireNonNull;

final class DoubleToLongFunctionMemoizer<KEY>
        extends AbstractMemoizer<KEY, Long>
        implements DoubleToLongFunction {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleToLongFunction function;

    DoubleToLongFunctionMemoizer(
            final ConcurrentMap<KEY, Long> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoubleToLongFunction function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL DoubleToLongFunction - provide an actual DoubleToLongFunction to fix this.");
    }

    @Override
    public long applyAsLong(final double value) {
        return computeIfAbsent(keyFunction.apply(value), key -> function.applyAsLong(value));
    }

}
