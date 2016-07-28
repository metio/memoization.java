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
import java.util.function.ToLongFunction;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedToLongFunctionMemoizer<INPUT, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Long>
        implements ToLongFunction<INPUT> {

    private final Function<INPUT, KEY>  keyFunction;
    private final ToLongFunction<INPUT> function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentMapBasedToLongFunctionMemoizer(
            final ConcurrentMap<KEY, Long> cache,
            final Function<INPUT, KEY> keyFunction,
            final ToLongFunction<INPUT> function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToLongFunction - provide an actual ToLongFunction to fix this.");
    }

    @Override
    public long applyAsLong(final INPUT input) {
        final KEY key = keyFunction.apply(input);
        return computeIfAbsent(key, givenKey -> Long.valueOf(function.applyAsLong(input)))
                .longValue();
    }

}
