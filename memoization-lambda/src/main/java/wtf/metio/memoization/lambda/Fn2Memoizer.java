/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.lambda;

import com.jnape.palatable.lambda.functions.Fn2;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Fn2Memoizer<KEY, TYPE1, TYPE2, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Fn2<TYPE1, TYPE2, OUTPUT> {

    private final Fn2<TYPE1, TYPE2, KEY> keyFunction;
    private final Fn2<TYPE1, TYPE2, OUTPUT> function;

    Fn2Memoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Fn2<TYPE1, TYPE2, KEY> keyFunction,
            final Fn2<TYPE1, TYPE2, OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Fn2 - provide an actual Fn2 to fix this.");
    }

    @Override
    public OUTPUT checkedApply(final TYPE1 v1, final TYPE2 v2) throws Throwable {
        try {
            return computeIfAbsent(keyFunction.apply(v1, v2), givenKey -> {
                try {
                    return function.checkedApply(v1, v2);
                } catch (final Throwable exception) {
                    throw new WrappedThrowable(exception);
                }
            });
        } catch (final WrappedThrowable exception) {
            throw exception.wrappedThrowable();
        }
    }

}
