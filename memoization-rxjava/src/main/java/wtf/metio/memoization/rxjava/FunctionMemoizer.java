/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.rxjava;

import io.reactivex.rxjava3.functions.Function;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class FunctionMemoizer<INPUT, KEY, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Function<INPUT, OUTPUT> {

    private final Function<INPUT, KEY> keyFunction;
    private final Function<INPUT, OUTPUT> function;

    FunctionMemoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Function<INPUT, KEY> keyFunction,
            final Function<INPUT, OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Function - provide an actual Function to fix this.");
    }

    @Override
    public OUTPUT apply(final INPUT input) throws Throwable {
        try {
            return computeIfAbsent(keyFunction.apply(input), key -> {
                try {
                    return function.apply(input);
                } catch (final Throwable exception) {
                    throw new WrappedThrowable(exception);
                }
            });
        } catch (final WrappedThrowable exception) {
            throw exception.wrappedThrowable();
        }
    }

}
