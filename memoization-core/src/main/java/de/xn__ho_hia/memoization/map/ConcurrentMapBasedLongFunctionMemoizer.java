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

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedLongFunctionMemoizer<KEY, OUTPUT>
        extends ConcurrentMapBasedMemoizer<KEY, OUTPUT>
        implements LongFunction<OUTPUT> {

    private final LongFunction<KEY>    keyFunction;
    private final LongFunction<OUTPUT> function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentMapBasedLongFunctionMemoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final LongFunction<KEY> keyFunction,
            final LongFunction<OUTPUT> function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = requireNonNull(function,
                "Cannot memoize a NULL LongFunction - provide an actual LongFunction to fix this.");
    }

    @Override
    public OUTPUT apply(final long value) {
        final KEY key = keyFunction.apply(value);
        return computeIfAbsent(key, givenKey -> function.apply(value));
    }

}
