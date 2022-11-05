/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.map;

import java.util.concurrent.ConcurrentMap;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

final class ConcurrentMapBasedDoubleSupplierMemoizer<KEY>
        extends ConcurrentMapBasedMemoizer<KEY, Double>
        implements DoubleSupplier {

    private final Supplier<KEY> keySupplier;
    private final DoubleSupplier supplier;

    ConcurrentMapBasedDoubleSupplierMemoizer(
            final ConcurrentMap<KEY, Double> cache,
            final Supplier<KEY> keySupplier,
            final DoubleSupplier supplier) {
        super(cache);
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a key function, might just be 'MemoizationDefaults.defaultKeySupplier()'.");
        this.supplier = requireNonNull(supplier,
                "Cannot memoize a NULL Supplier - provide an actual Supplier to fix this.");
    }

    @Override
    public double getAsDouble() {
        return computeIfAbsent(keySupplier.get(), input -> supplier.getAsDouble());
    }

}
