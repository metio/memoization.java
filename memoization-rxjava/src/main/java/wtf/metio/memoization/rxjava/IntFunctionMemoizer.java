/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.rxjava;

import io.reactivex.rxjava3.functions.IntFunction;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class IntFunctionMemoizer<KEY, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements IntFunction<OUTPUT> {

    private final IntFunction<KEY> keyFunction;
    private final IntFunction<OUTPUT> function;

    IntFunctionMemoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final IntFunction<KEY> keyFunction,
            final IntFunction<OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL IntFunction - provide an actual IntFunction to fix this.");
    }

    @Override
    public OUTPUT apply(final int number) throws Throwable {
        try {
            return computeIfAbsent(keyFunction.apply(number), key -> {
                try {
                    return function.apply(number);
                } catch (final Throwable exception) {
                    throw new WrappedThrowable(exception);
                }
            });
        } catch (final WrappedThrowable exception) {
            throw exception.wrappedThrowable();
        }
    }

}
