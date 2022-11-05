/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedLongSupplierMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Long>
        implements LongSupplier {

    private final Supplier<KEY> keySupplier;
    private final LongSupplier supplier;

    ConcurrentMapBasedLongSupplierMemoizer(
            final ConcurrentMap<KEY, Long> cache,
            final Supplier<KEY> keySupplier,
            final LongSupplier supplier) {
        super(cache);
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a key function, might just be 'MemoizationDefaults.defaultKeySupplier()'.");
        this.supplier = requireNonNull(supplier,
                "Cannot memoize a NULL Supplier - provide an actual Supplier to fix this.");
    }

    @Override
    public long getAsLong() {
        return computeIfAbsent(keySupplier.get(), input -> supplier.getAsLong());
    }

}
