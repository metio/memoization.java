/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.ToLongFunction;

import static java.util.Objects.requireNonNull;

final class ToLongFunctionMemoizer<INPUT, KEY>
        extends AbstractMemoizer<KEY, Long>
        implements ToLongFunction<INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final ToLongFunction<INPUT> function;

    ToLongFunctionMemoizer(
            final ConcurrentMap<KEY, Long> cache,
            final Function<INPUT, KEY> keyFunction,
            final ToLongFunction<INPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToLongFunction - provide an actual ToLongFunction to fix this.");
    }

    @Override
    public long applyAsLong(final INPUT input) {
        return computeIfAbsent(keyFunction.apply(input), key -> function.applyAsLong(input));
    }

}
