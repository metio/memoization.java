/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */

package wtf.metio.memoization.lambda;

import com.jnape.palatable.lambda.functions.Fn0;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

final class Fn0Memoizer<KEY, OUTPUT>
        extends AbstractMemoizer<KEY, OUTPUT>
        implements Fn0<OUTPUT> {

    private final Supplier<KEY> keySupplier;
    private final Fn0<OUTPUT> function;

    Fn0Memoizer(
            final ConcurrentMap<KEY, OUTPUT> cache,
            final Supplier<KEY> keySupplier,
            final Fn0<OUTPUT> function) {
        super(cache);
        this.keySupplier = requireNonNull(keySupplier, "Provide a key supplier.");
        this.function = requireNonNull(function,
                "Cannot memoize a NULL Fn0 - provide an actual Fn0 to fix this.");
    }

    @Override
    public OUTPUT checkedApply() throws Throwable {
        try {
            return computeIfAbsent(keySupplier.get(), givenKey -> {
                try {
                    return function.checkedApply();
                } catch (final Throwable exception) {
                    throw new WrappedThrowable(exception);
                }
            });
        } catch (final WrappedThrowable exception) {
            throw exception.wrappedThrowable();
        }
    }

}
