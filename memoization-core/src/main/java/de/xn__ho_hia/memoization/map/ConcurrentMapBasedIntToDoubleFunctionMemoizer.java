/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntToDoubleFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedIntToDoubleFunctionMemoizer
        extends ConcurrentMapBasedMemoizer<Integer, Double>
        implements IntToDoubleFunction {

    private final IntToDoubleFunction function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentMapBasedIntToDoubleFunctionMemoizer(
            final ConcurrentMap<Integer, Double> cache,
            final IntToDoubleFunction function) {
        super(cache);
        this.function = requireNonNull(function,
                "Cannot memoize a NULL IntToDoubleFunction - provide an actual IntToDoubleFunction to fix this.");
    }

    @Override
    public double applyAsDouble(final int value) {
        final Integer key = Integer.valueOf(value);
        return computeIfAbsent(key, givenKey -> Double.valueOf(function.applyAsDouble(value)))
                .doubleValue();
    }

}
