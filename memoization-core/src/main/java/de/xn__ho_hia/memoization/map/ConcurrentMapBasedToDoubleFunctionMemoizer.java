/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedToDoubleFunctionMemoizer<INPUT, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Double>
        implements ToDoubleFunction<INPUT> {

    private final Function<INPUT, KEY>    keyFunction;
    private final ToDoubleFunction<INPUT> function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentMapBasedToDoubleFunctionMemoizer(
            final ConcurrentMap<KEY, Double> cache,
            final Function<INPUT, KEY> keyFunction,
            final ToDoubleFunction<INPUT> function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToDoubleFunction - provide an actual ToDoubleFunction to fix this.");
    }

    @Override
    public double applyAsDouble(final INPUT value) {
        final KEY key = keyFunction.apply(value);
        return computeIfAbsent(key, givenKey -> Double.valueOf(function.applyAsDouble(value)))
                .doubleValue();
    }

}
