/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedToDoubleFunctionMemoizer<INPUT, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Double>
        implements ToDoubleFunction<INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final ToDoubleFunction<INPUT> function;

    ConcurrentMapBasedToDoubleFunctionMemoizer(
            final ConcurrentMap<KEY, Double> cache,
            final Function<INPUT, KEY> keyFunction,
            final ToDoubleFunction<INPUT> function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToDoubleFunction - provide an actual ToDoubleFunction to fix this.");
    }

    @Override
    public double applyAsDouble(final INPUT value) {
        final KEY key = keyFunction.apply(value);
        return computeIfAbsent(key, givenKey -> function.applyAsDouble(value));
    }

}
