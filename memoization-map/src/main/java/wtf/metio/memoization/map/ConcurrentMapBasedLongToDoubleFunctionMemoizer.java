/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedLongToDoubleFunctionMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Double>
        implements LongToDoubleFunction {

    private final LongFunction<KEY> keyFunction;
    private final LongToDoubleFunction function;

    ConcurrentMapBasedLongToDoubleFunctionMemoizer(
            final ConcurrentMap<KEY, Double> cache,
            final LongFunction<KEY> keyFunction,
            final LongToDoubleFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = requireNonNull(function,
                "Cannot memoize a NULL LongToDoubleFunction - provide an actual LongToDoubleFunction to fix this.");
    }

    @Override
    public double applyAsDouble(final long value) {
        final KEY key = keyFunction.apply(value);
        return computeIfAbsent(key, givenKey -> function.applyAsDouble(value));
    }

}
