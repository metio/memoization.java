/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.ToDoubleBiFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedToDoubleBiFunctionMemoizer<FIRST, SECOND, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Double>
        implements ToDoubleBiFunction<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final ToDoubleBiFunction<FIRST, SECOND> function;

    ConcurrentMapBasedToDoubleBiFunctionMemoizer(
            final ConcurrentMap<KEY, Double> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final ToDoubleBiFunction<FIRST, SECOND> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToDoubleBiFunction - provide an actual ToDoubleBiFunction to fix this.");
    }

    @Override
    public double applyAsDouble(final FIRST t, final SECOND u) {
        final KEY key = keyFunction.apply(t, u);
        return computeIfAbsent(key, givenKey -> function.applyAsDouble(t, u));
    }

}
