/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedIntSupplierMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Integer>
        implements IntSupplier {

    private final Supplier<KEY> keySupplier;
    private final IntSupplier supplier;

    ConcurrentMapBasedIntSupplierMemoizer(
            final ConcurrentMap<KEY, Integer> cache,
            final Supplier<KEY> keySupplier,
            final IntSupplier supplier) {
        super(cache);
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a key function, might just be 'MemoizationDefaults.defaultKeySupplier()'.");
        this.supplier = requireNonNull(supplier,
                "Cannot memoize a NULL Supplier - provide an actual Supplier to fix this.");
    }

    @Override
    public int getAsInt() {
        return computeIfAbsent(keySupplier.get(), input -> supplier.getAsInt());
    }

}
