/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.ToLongBiFunction;

import static java.util.Objects.requireNonNull;

final class ToLongBiFunctionMemoizer<FIRST, SECOND, KEY>
        extends AbstractMemoizer<KEY, Long>
        implements ToLongBiFunction<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final ToLongBiFunction<FIRST, SECOND> function;

    ToLongBiFunctionMemoizer(
            final ConcurrentMap<KEY, Long> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final ToLongBiFunction<FIRST, SECOND> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults::hashCodes'.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToLongBiFunction - provide an actual ToLongBiFunction to fix this.");
    }

    @Override
    public long applyAsLong(final FIRST first, final SECOND second) {
        return computeIfAbsent(keyFunction.apply(first, second), key -> function.applyAsLong(first, second));
    }

}
