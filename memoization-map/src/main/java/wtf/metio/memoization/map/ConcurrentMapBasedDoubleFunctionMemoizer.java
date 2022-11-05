/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleFunction;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedDoubleFunctionMemoizer<KEY, OUTPUT>
        extends ConcurrentMapBasedMemoizer<KEY, OUTPUT>
        implements DoubleFunction<OUTPUT> {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleFunction<OUTPUT> function;

    ConcurrentMapBasedDoubleFunctionMemoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoubleFunction<OUTPUT> function) {
        super(cache);
        this.keyFunction = keyFunction;
        this.function = requireNonNull(function,
                "Cannot memoize a NULL DoubleFunction - provide an actual DoubleFunction to fix this.");
    }

    @Override
    public OUTPUT apply(final double value) {
        final KEY key = keyFunction.apply(value);
        return computeIfAbsent(key, givenKey -> function.apply(value));
    }

}
