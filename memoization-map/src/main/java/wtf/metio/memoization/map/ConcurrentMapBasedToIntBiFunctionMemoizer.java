/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.ToIntBiFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedToIntBiFunctionMemoizer<FIRST, SECOND, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Integer>
        implements ToIntBiFunction<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final ToIntBiFunction<FIRST, SECOND> function;

    ConcurrentMapBasedToIntBiFunctionMemoizer(
            final ConcurrentMap<KEY, Integer> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final ToIntBiFunction<FIRST, SECOND> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToIntBiFunction - provide an actual ToIntBiFunction to fix this.");
    }

    @Override
    public int applyAsInt(final FIRST t, final SECOND u) {
        final KEY key = keyFunction.apply(t, u);
        return computeIfAbsent(key, givenKey -> function.applyAsInt(t, u));
    }

}
