/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.lambda;

import com.jnape.palatable.lambda.functions.Fn8;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Fn8Memoizer<KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> {

    private final Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction;
    private final Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function;

    Fn8Memoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, KEY> keyFunction,
            final Fn8<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Fn8 - provide an actual Fn8 to fix this.");
    }

    @Override
    public OUTPUT checkedApply(final TYPE1 v1, final TYPE2 v2, final TYPE3 v3, final TYPE4 v4, final TYPE5 v5, final TYPE6 v6, final TYPE7 v7, final TYPE8 v8) throws Throwable {
        try {
            return computeIfAbsent(keyFunction.apply(v1, v2, v3, v4, v5, v6, v7, v8), givenKey -> {
                try {
                    return function.checkedApply(v1, v2, v3, v4, v5, v6, v7, v8);
                } catch (final Throwable exception) {
                    throw new WrappedThrowable(exception);
                }
            });
        } catch (final WrappedThrowable exception) {
            throw exception.wrappedThrowable();
        }
    }

}
