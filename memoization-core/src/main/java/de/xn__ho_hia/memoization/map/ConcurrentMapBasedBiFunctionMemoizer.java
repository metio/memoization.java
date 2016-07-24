/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import de.xn__ho_hia.memoization.shared.MemoizingBiFunction;
import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedBiFunctionMemoizer<FIRST, SECOND, KEY, VALUE>
        extends ConcurrentMapBasedMemoizer<KEY, VALUE>
        implements MemoizingBiFunction<FIRST, SECOND, KEY, VALUE> {

    private final BiFunction<FIRST, SECOND, KEY>   keyFunction;
    private final BiFunction<FIRST, SECOND, VALUE> biFunction;

    @SuppressWarnings(CompilerWarnings.NLS)
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
