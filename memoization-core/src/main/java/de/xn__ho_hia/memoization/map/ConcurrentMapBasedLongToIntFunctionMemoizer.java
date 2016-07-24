/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongToIntFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedLongToIntFunctionMemoizer
        extends ConcurrentMapBasedMemoizer<Long, Integer>
        implements LongToIntFunction {

    private final LongToIntFunction function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentMapBasedLongToIntFunctionMemoizer(
            final ConcurrentMap<Long, Integer> cache,
            final LongToIntFunction function) {
        super(cache);
        this.function = requireNonNull(function,
                "Cannot memoize a NULL LongToIntFunction - provide an actual LongToIntFunction to fix this.");
    }

    @Override
    public int applyAsInt(final long value) {
        final Long key = Long.valueOf(value);
        return computeIfAbsent(key, givenKey -> Integer.valueOf(function.applyAsInt(value)))
                .intValue();
    }

}
