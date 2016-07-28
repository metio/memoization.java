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

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedFunctionMemoizer<INPUT, KEY, OUTPUT>
        extends ConcurrentMapBasedMemoizer<KEY, OUTPUT>
        implements Function<INPUT, OUTPUT> {

    private final Function<INPUT, KEY>    keyFunction;
    private final Function<INPUT, OUTPUT> function;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentMapBasedFunctionMemoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Function<INPUT, KEY> keyFunction,
            final Function<INPUT, OUTPUT> function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Function - provide an actual Function to fix this.");
    }

    @Override
    public OUTPUT apply(final INPUT input) {
        final KEY key = keyFunction.apply(input);
        return computeIfAbsent(key, givenKey -> function.apply(input));
    }

}
