/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedDoubleToIntFunctionMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Integer>
        implements DoubleToIntFunction {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleToIntFunction function;

    ConcurrentMapBasedDoubleToIntFunctionMemoizer(
            final ConcurrentMap<KEY, Integer> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoubleToIntFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = requireNonNull(function,
                "Cannot memoize a NULL DoubleToIntFunction - provide an actual DoubleToIntFunction to fix this.");
    }

    @Override
    public int applyAsInt(final double value) {
        final KEY key = keyFunction.apply(value);
        return computeIfAbsent(key, givenKey -> function.applyAsInt(value));
    }

}
