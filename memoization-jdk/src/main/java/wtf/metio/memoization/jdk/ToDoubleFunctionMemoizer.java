/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

import static java.util.Objects.requireNonNull;

final class ToDoubleFunctionMemoizer<INPUT, KEY>
        extends AbstractMemoizer<KEY, Double>
        implements ToDoubleFunction<INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final ToDoubleFunction<INPUT> function;

    ToDoubleFunctionMemoizer(
            final ConcurrentMap<KEY, Double> cache,
            final Function<INPUT, KEY> keyFunction,
            final ToDoubleFunction<INPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToDoubleFunction - provide an actual ToDoubleFunction to fix this.");
    }

    @Override
    public double applyAsDouble(final INPUT value) {
        return computeIfAbsent(keyFunction.apply(value), key -> function.applyAsDouble(value));
    }

}
