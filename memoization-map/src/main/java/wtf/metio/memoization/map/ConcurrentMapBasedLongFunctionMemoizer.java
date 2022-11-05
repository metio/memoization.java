/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedLongFunctionMemoizer<KEY, OUTPUT>
        extends ConcurrentMapBasedMemoizer<KEY, OUTPUT>
        implements LongFunction<OUTPUT> {

    private final LongFunction<KEY> keyFunction;
    private final LongFunction<OUTPUT> function;

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
