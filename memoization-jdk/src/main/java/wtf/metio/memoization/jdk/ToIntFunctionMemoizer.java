/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import static java.util.Objects.requireNonNull;

final class ToIntFunctionMemoizer<INPUT, KEY>
        extends AbstractMemoizer<KEY, Integer>
        implements ToIntFunction<INPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final ToIntFunction<INPUT> function;

    ToIntFunctionMemoizer(
            final ConcurrentMap<KEY, Integer> cache,
            final Function<INPUT, KEY> keyFunction,
            final ToIntFunction<INPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL ToIntFunction - provide an actual ToIntFunction to fix this.");
    }

    @Override
    public int applyAsInt(final INPUT input) {
        return computeIfAbsent(keyFunction.apply(input), key -> function.applyAsInt(input));
    }

}
