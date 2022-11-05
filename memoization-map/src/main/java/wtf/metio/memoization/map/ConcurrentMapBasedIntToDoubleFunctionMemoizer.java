/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedIntToDoubleFunctionMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Double>
        implements IntToDoubleFunction {

    private final IntFunction<KEY> keyFunction;
    private final IntToDoubleFunction function;

    ConcurrentMapBasedIntToDoubleFunctionMemoizer(
            final ConcurrentMap<KEY, Double> cache,
            final IntFunction<KEY> keyFunction,
            final IntToDoubleFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = requireNonNull(function,
                "Cannot memoize a NULL IntToDoubleFunction - provide an actual IntToDoubleFunction to fix this.");
    }

    @Override
    public double applyAsDouble(final int value) {
        final KEY key = keyFunction.apply(value);
        return computeIfAbsent(key, givenKey -> function.applyAsDouble(value));
    }

}
