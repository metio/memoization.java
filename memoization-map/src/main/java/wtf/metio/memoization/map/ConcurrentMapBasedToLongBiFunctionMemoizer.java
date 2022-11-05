/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.ToLongBiFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedToLongBiFunctionMemoizer<FIRST, SECOND, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Long>
        implements ToLongBiFunction<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final ToLongBiFunction<FIRST, SECOND> function;

    ConcurrentMapBasedToLongBiFunctionMemoizer(
            final ConcurrentMap<KEY, Long> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final ToLongBiFunction<FIRST, SECOND> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToLongBiFunction - provide an actual ToLongBiFunction to fix this.");
    }

    @Override
    public long applyAsLong(final FIRST t, final SECOND u) {
        final KEY key = keyFunction.apply(t, u);
        return computeIfAbsent(key, givenKey -> function.applyAsLong(t, u));
    }

}
