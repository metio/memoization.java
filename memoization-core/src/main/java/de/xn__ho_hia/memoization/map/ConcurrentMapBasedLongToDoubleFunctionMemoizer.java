/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedLongToDoubleFunctionMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Double>
        implements LongToDoubleFunction {

    private final LongFunction<KEY>    keyFunction;
    private final LongToDoubleFunction function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentMapBasedLongToDoubleFunctionMemoizer(
            final ConcurrentMap<KEY, Double> cache,
            final LongFunction<KEY> keyFunction,
            final LongToDoubleFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = requireNonNull(function,
                "Cannot memoize a NULL LongToDoubleFunction - provide an actual LongToDoubleFunction to fix this.");
    }

    @Override
    public double applyAsDouble(final long value) {
        final KEY key = keyFunction.apply(value);
        return computeIfAbsent(key, givenKey -> Double.valueOf(function.applyAsDouble(value)))
                .doubleValue();
    }

}
