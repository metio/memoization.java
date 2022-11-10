/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;

import static java.util.Objects.requireNonNull;

final class BiFunctionMemoizer<FIRST, SECOND, KEY, VALUE>
        extends AbstractMemoizer<KEY, VALUE>
        implements BiFunction<FIRST, SECOND, VALUE> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final BiFunction<FIRST, SECOND, VALUE> biFunction;

    BiFunctionMemoizer(
            final ConcurrentMap<KEY, VALUE> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiFunction<FIRST, SECOND, VALUE> biFunction) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults::hashCodes'.");
        this.biFunction = requireNonNull(biFunction,
                "Cannot memoize a NULL BiFunction - provide an actual BiFunction to fix this.");
    }

    @Override
    public VALUE apply(final FIRST first, final SECOND second) {
        return computeIfAbsent(keyFunction.apply(first, second), key -> biFunction.apply(first, second));
    }

}
