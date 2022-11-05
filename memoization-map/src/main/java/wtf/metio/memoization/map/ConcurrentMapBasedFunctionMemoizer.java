/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedFunctionMemoizer<INPUT, KEY, OUTPUT>
        extends ConcurrentMapBasedMemoizer<KEY, OUTPUT>
        implements Function<INPUT, OUTPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final Function<INPUT, OUTPUT> function;

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
