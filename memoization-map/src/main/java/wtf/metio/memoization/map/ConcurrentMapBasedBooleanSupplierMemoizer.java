/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedBooleanSupplierMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Boolean>
        implements BooleanSupplier {

    private final Supplier<KEY> keySupplier;
    private final BooleanSupplier supplier;

    ConcurrentMapBasedBooleanSupplierMemoizer(
            final ConcurrentMap<KEY, Boolean> cache,
            final Supplier<KEY> keySupplier,
            final BooleanSupplier supplier) {
        super(cache);
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a key function, might just be 'MemoizationDefaults.defaultKeySupplier()'.");
        this.supplier = requireNonNull(supplier,
                "Cannot memoize a NULL Supplier - provide an actual Supplier to fix this.");
    }

    @Override
    public boolean getAsBoolean() {
        return computeIfAbsent(keySupplier.get(), input -> supplier.getAsBoolean());
    }

}
