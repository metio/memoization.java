/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.lambda;

import com.jnape.palatable.lambda.functions.Fn3;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Fn3Memoizer<KEY, TYPE1, TYPE2, TYPE3, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> {

    private final Fn3<TYPE1, TYPE2, TYPE3, KEY> keyFunction;
    private final Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> function;

    Fn3Memoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Fn3<TYPE1, TYPE2, TYPE3, KEY> keyFunction,
            final Fn3<TYPE1, TYPE2, TYPE3, OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Fn3 - provide an actual Fn3 to fix this.");
    }

    @Override
    public OUTPUT checkedApply(final TYPE1 v1, final TYPE2 v2, final TYPE3 v3) throws Throwable {
        try {
            return computeIfAbsent(keyFunction.apply(v1, v2, v3), givenKey -> {
                try {
                    return function.checkedApply(v1, v2, v3);
                } catch (final Throwable exception) {
                    throw new WrappedThrowable(exception);
                }
            });
        } catch (final WrappedThrowable exception) {
            throw exception.wrappedThrowable();
        }
    }

}
