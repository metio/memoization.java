/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

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
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a key function, might just be 'MemoizationDefaults.staticKey()'.");
        this.supplier = requireNonNull(supplier,
                "Cannot memoize a NULL Supplier - provide an actual Supplier to fix this.");
    }

    @Override
    public VALUE get() {
        return computeIfAbsent(keySupplier.get(), input -> supplier.get());
    }

}
