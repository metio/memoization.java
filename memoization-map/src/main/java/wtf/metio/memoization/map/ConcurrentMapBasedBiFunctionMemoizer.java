/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import wtf.metio.memoization.core.MemoizingBiFunction;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedBiFunctionMemoizer<FIRST, SECOND, KEY, VALUE>
        extends ConcurrentMapBasedMemoizer<KEY, VALUE>
        implements MemoizingBiFunction<FIRST, SECOND, KEY, VALUE> {

    private final BiFunction<FIRST, SECOND, KEY> keyFunction;
    private final BiFunction<FIRST, SECOND, VALUE> biFunction;

    ConcurrentMapBasedBiFunctionMemoizer(
            final ConcurrentMap<KEY, VALUE> cache,
            final BiFunction<FIRST, SECOND, KEY> keyFunction,
            final BiFunction<FIRST, SECOND, VALUE> biFunction) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction,
                "Provide a key function, might just be 'MemoizationDefaults.hashCodeKeyFunction()'.");
        this.biFunction = requireNonNull(biFunction,
                "Cannot memoize a NULL BiFunction - provide an actual BiFunction to fix this.");
    }

    @Override
    public BiFunction<FIRST, SECOND, KEY> getKeyFunction() {
        return keyFunction;
    }

    @Override
    public BiFunction<FIRST, SECOND, VALUE> getBiFunction() {
        return biFunction;
    }

    @Override
    public BiFunction<KEY, Function<KEY, VALUE>, VALUE> getMemoizingFunction() {
        return this::computeIfAbsent;
    }

}
