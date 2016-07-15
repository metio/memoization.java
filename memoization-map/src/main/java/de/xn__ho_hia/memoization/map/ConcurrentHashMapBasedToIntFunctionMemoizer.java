/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.ToIntFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedToIntFunctionMemoizer<VALUE>
        extends ConcurrentHashMapBasedMemoizer<VALUE, Integer>
        implements ToIntFunction<VALUE> {

    private final ToIntFunction<VALUE> function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedToIntFunctionMemoizer(
            final Map<VALUE, Integer> preComputedValues,
            final ToIntFunction<VALUE> function) {
        super(preComputedValues);
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToIntFunction - provide an actual ToIntFunction to fix this.");
    }

    @Override
    public int applyAsInt(final VALUE value) {
        return computeIfAbsent(value, givenKey -> Integer.valueOf(function.applyAsInt(value)))
                .intValue();
    }

}
