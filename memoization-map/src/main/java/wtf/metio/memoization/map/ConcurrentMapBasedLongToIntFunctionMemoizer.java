/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongFunction;
import java.util.function.LongToIntFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedLongToIntFunctionMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Integer>
        implements LongToIntFunction {

    private final LongFunction<KEY> keyFunction;
    private final LongToIntFunction function;

    ConcurrentMapBasedLongToIntFunctionMemoizer(
            final ConcurrentMap<KEY, Integer> cache,
            final LongFunction<KEY> keyFunction,
            final LongToIntFunction function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = requireNonNull(function,
                "Cannot memoize a NULL LongToIntFunction - provide an actual LongToIntFunction to fix this.");
    }

    @Override
    public int applyAsInt(final long value) {
        final KEY key = keyFunction.apply(value);
        return computeIfAbsent(key, givenKey -> function.applyAsInt(value));
    }

}
