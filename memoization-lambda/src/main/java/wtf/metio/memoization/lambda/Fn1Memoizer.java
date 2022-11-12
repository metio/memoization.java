/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.lambda;

import com.jnape.palatable.lambda.functions.Fn1;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Fn1Memoizer<KEY, INPUT, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Fn1<INPUT, OUTPUT> {

    private final Fn1<INPUT, KEY> keyFunction;
    private final Fn1<INPUT, OUTPUT> function;

    Fn1Memoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Fn1<INPUT, KEY> keyFunction,
            final Fn1<INPUT, OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Fn1 - provide an actual Fn1 to fix this.");
    }

    @Override
    public OUTPUT checkedApply(final INPUT input) throws Throwable {
        try {
            return computeIfAbsent(keyFunction.apply(input), givenKey -> {
                try {
                    return function.checkedApply(input);
                } catch (final Throwable exception) {
                    throw new WrappedThrowable(exception);
                }
            });
        } catch (final WrappedThrowable exception) {
            throw exception.wrappedThrowable();
        }
    }

}
