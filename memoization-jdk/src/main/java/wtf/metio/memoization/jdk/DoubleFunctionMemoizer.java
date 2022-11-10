/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleFunction;

import static java.util.Objects.requireNonNull;

final class DoubleFunctionMemoizer<KEY, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements DoubleFunction<OUTPUT> {

    private final DoubleFunction<KEY> keyFunction;
    private final DoubleFunction<OUTPUT> function;

    DoubleFunctionMemoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final DoubleFunction<KEY> keyFunction,
            final DoubleFunction<OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL DoubleFunction - provide an actual DoubleFunction to fix this.");
    }

    @Override
    public OUTPUT apply(final double value) {
        return computeIfAbsent(keyFunction.apply(value), key -> function.apply(value));
    }

}
