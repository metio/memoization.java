/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.ToLongFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedToLongFunctionMemoizer<VALUE>
        extends ConcurrentMapBasedMemoizer<VALUE, Long>
        implements ToLongFunction<VALUE> {

    private final ToLongFunction<VALUE> function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentMapBasedToLongFunctionMemoizer(
            final ConcurrentMap<VALUE, Long> cache,
            final ToLongFunction<VALUE> function) {
        super(cache);
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToLongFunction - provide an actual ToLongFunction to fix this.");
    }

    @Override
    public long applyAsLong(final VALUE value) {
        return computeIfAbsent(value, givenKey -> Long.valueOf(function.applyAsLong(value)))
                .longValue();
    }

}
