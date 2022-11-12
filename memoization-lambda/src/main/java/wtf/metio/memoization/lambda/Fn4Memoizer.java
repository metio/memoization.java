/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.lambda;

import com.jnape.palatable.lambda.functions.Fn4;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Fn4Memoizer<KEY, TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> {

    private final Fn4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction;
    private final Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function;

    Fn4Memoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Fn4<TYPE1, TYPE2, TYPE3, TYPE4, KEY> keyFunction,
            final Fn4<TYPE1, TYPE2, TYPE3, TYPE4, OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Fn4 - provide an actual Fn4 to fix this.");
    }

    @Override
    public OUTPUT checkedApply(final TYPE1 v1, final TYPE2 v2, final TYPE3 v3, final TYPE4 v4) throws Throwable {
        try {
            return computeIfAbsent(keyFunction.apply(v1, v2, v3, v4), givenKey -> {
                try {
                    return function.checkedApply(v1, v2, v3, v4);
                } catch (final Throwable exception) {
                    throw new WrappedThrowable(exception);
                }
            });
        } catch (final WrappedThrowable exception) {
            throw exception.wrappedThrowable();
        }
    }

}
