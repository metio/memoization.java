/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.ToIntBiFunction;

import static java.util.Objects.requireNonNull;

final class ToIntBiFunctionMemoizer<FIRST, SECOND, KEY>
        extends AbstractMemoizer<KEY, Integer>
        implements ToIntBiFunction<FIRST, SECOND> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final ToIntBiFunction<FIRST, SECOND> function;

    ToIntBiFunctionMemoizer(
            final ConcurrentMap<KEY, Integer> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final ToIntBiFunction<FIRST, SECOND> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults::hashCodes'.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToIntBiFunction - provide an actual ToIntBiFunction to fix this.");
    }

    @Override
    public int applyAsInt(final FIRST first, final SECOND second) {
        return computeIfAbsent(keyFunction.apply(first, second), key -> function.applyAsInt(first, second));
    }

}
