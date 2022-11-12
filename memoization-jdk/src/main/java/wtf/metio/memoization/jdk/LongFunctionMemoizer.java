/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongFunction;

import static java.util.Objects.requireNonNull;

final class LongFunctionMemoizer<KEY, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements LongFunction<OUTPUT> {

    private final LongFunction<KEY> keyFunction;
    private final LongFunction<OUTPUT> function;

    LongFunctionMemoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final LongFunction<KEY> keyFunction,
            final LongFunction<OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL LongFunction - provide an actual LongFunction to fix this.");
    }

    @Override
    public OUTPUT apply(final long value) {
        return computeIfAbsent(keyFunction.apply(value), key -> function.apply(value));
    }

}
