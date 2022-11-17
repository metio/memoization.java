/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.rxjava;

import io.reactivex.rxjava3.functions.Supplier;
import wtf.metio.memoization.core.AbstractMemoizer;
import wtf.metio.memoization.core.WrappedThrowable;

import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

final class SupplierMemoizer<KEY, VALUE>
        extends AbstractMemoizer<KEY, VALUE>
        implements Supplier<VALUE> {

    private final Supplier<KEY> keySupplier;
    private final Supplier<VALUE> supplier;

    SupplierMemoizer(
            final ConcurrentMap<KEY, VALUE> cache,
            final Supplier<KEY> keySupplier,
            final Supplier<VALUE> supplier) {
        super(cache);
        this.keySupplier = requireNonNull(keySupplier, "Provide a key supplier.");
        this.supplier = requireNonNull(supplier,
                "Cannot memoize a NULL Supplier - provide an actual Supplier to fix this.");
    }

    @Override
    public VALUE get() throws Throwable {
        try {
            return computeIfAbsent(keySupplier.get(), key -> {
                try {
                    return supplier.get();
                } catch (final Throwable exception) {
                    throw new WrappedThrowable(exception);
                }
            });
        } catch (final WrappedThrowable exception) {
            throw exception.wrappedThrowable();
        }
    }

}
