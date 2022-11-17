/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.rxjava;

import io.reactivex.rxjava3.functions.BooleanSupplier;
import io.reactivex.rxjava3.functions.Supplier;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class BooleanSupplierMemoizer<KEY>
        extends AbstractMemoizer<KEY, Boolean>
        implements BooleanSupplier {

    private final Supplier<KEY> keySupplier;
    private final BooleanSupplier supplier;

    BooleanSupplierMemoizer(
            final ConcurrentMap<KEY, Boolean> cache,
            final Supplier<KEY> keySupplier,
            final BooleanSupplier supplier) {
        super(cache);
        this.keySupplier = requireNonNull(keySupplier, "Provide a key supplier.");
        this.supplier = requireNonNull(supplier,
                "Cannot memoize a NULL BooleanSupplier - provide an actual BooleanSupplier to fix this.");
    }

    @Override
    public boolean getAsBoolean() throws Throwable {
        try {
            return computeIfAbsent(keySupplier.get(), key -> {
                try {
                    return supplier.getAsBoolean();
                } catch (final Throwable exception) {
                    throw new WrappedThrowable(exception);
                }
            });
        } catch (final WrappedThrowable exception) {
            throw exception.wrappedThrowable();
        }
    }

}
