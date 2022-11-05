/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.ToLongFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedToLongFunctionMemoizer<INPUT, KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Long>
        implements ToLongFunction<INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final ToLongFunction<INPUT> function;

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
        return computeIfAbsent(key, givenKey -> function.applyAsLong(input));
    }

}
