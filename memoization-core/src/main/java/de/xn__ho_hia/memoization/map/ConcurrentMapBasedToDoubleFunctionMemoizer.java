/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.ToDoubleFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedToDoubleFunctionMemoizer<VALUE>
        extends ConcurrentMapBasedMemoizer<VALUE, Double>
        implements ToDoubleFunction<VALUE> {

    private final ToDoubleFunction<VALUE> function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentMapBasedToDoubleFunctionMemoizer(
            final ConcurrentMap<VALUE, Double> cache,
            final ToDoubleFunction<VALUE> function) {
        super(cache);
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToDoubleFunction - provide an actual ToDoubleFunction to fix this.");
    }

    @Override
    public double applyAsDouble(final VALUE value) {
        return computeIfAbsent(value, givenKey -> Double.valueOf(function.applyAsDouble(value)))
                .doubleValue();
    }

}
