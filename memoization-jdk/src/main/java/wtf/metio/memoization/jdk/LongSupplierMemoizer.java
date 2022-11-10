/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

final class LongSupplierMemoizer<KEY>
        extends AbstractMemoizer<KEY, Long>
        implements LongSupplier {

    private final Supplier<KEY> keySupplier;
    private final LongSupplier supplier;

    LongSupplierMemoizer(
            final ConcurrentMap<KEY, Long> cache,
            final Supplier<KEY> keySupplier,
            final LongSupplier supplier) {
        super(cache);
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a key function, might just be 'MemoizationDefaults.staticKey()'.");
        this.supplier = requireNonNull(supplier,
                "Cannot memoize a NULL Supplier - provide an actual Supplier to fix this.");
    }

    @Override
    public long getAsLong() {
        return computeIfAbsent(keySupplier.get(), input -> supplier.getAsLong());
    }

}
