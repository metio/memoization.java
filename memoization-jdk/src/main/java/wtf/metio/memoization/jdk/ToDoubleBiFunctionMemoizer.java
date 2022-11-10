/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.ToDoubleBiFunction;

import static java.util.Objects.requireNonNull;

final class ToDoubleBiFunctionMemoizer<FIRST, SECOND, KEY>
        extends AbstractMemoizer<KEY, Double>
        implements ToDoubleBiFunction<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final ToDoubleBiFunction<FIRST, SECOND> function;

    ToDoubleBiFunctionMemoizer(
            final ConcurrentMap<KEY, Double> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final ToDoubleBiFunction<FIRST, SECOND> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults::hashCodes'.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToDoubleBiFunction - provide an actual ToDoubleBiFunction to fix this.");
    }

    @Override
    public double applyAsDouble(final FIRST first, final SECOND second) {
        return computeIfAbsent(keyFunction.apply(first, second), key -> function.applyAsDouble(first, second));
    }

}
