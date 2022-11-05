/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToLongFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedDoubleToLongFunctionMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Long>
        implements DoubleToLongFunction {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleToLongFunction function;

    ConcurrentMapBasedDoubleToLongFunctionMemoizer(
            final ConcurrentMap<KEY, Long> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoubleToLongFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = requireNonNull(function,
                "Cannot memoize a NULL DoubleToLongFunction - provide an actual DoubleToLongFunction to fix this.");
    }

    @Override
    public long applyAsLong(final double value) {
        final KEY key = keyFunction.apply(value);
        return computeIfAbsent(key, givenKey -> function.applyAsLong(value));
    }

}
