/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.rxjava;

import io.reactivex.rxjava3.functions.Function9;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class Function9Memoizer<KEY, TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> {

    private final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, KEY> keyFunction;
    private final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function;

    Function9Memoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, KEY> keyFunction,
            final Function9<TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, TYPE6, TYPE7, TYPE8, TYPE9, OUTPUT> function) {
        super(cache);
        this.keyFunction = requireNonNull(keyFunction, "Provide a key function.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Function9 - provide an actual Function9 to fix this.");
    }

    @Override
    public OUTPUT apply(final TYPE1 v1, final TYPE2 v2, final TYPE3 v3, final TYPE4 v4, final TYPE5 v5, final TYPE6 v6, final TYPE7 v7, final TYPE8 v8, final TYPE9 v9) throws Throwable {
        try {
            return computeIfAbsent(keyFunction.apply(v1, v2, v3, v4, v5, v6, v7, v8, v9), key -> {
                try {
                    return function.apply(v1, v2, v3, v4, v5, v6, v7, v8, v9);
                } catch (final Throwable exception) {
                    throw new WrappedThrowable(exception);
                }
            });
        } catch (final WrappedThrowable exception) {
            throw exception.wrappedThrowable();
        }
    }

}
