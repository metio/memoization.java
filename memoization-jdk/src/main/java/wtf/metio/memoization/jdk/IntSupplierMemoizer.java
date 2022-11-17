/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

final class IntSupplierMemoizer<KEY>
        extends AbstractMemoizer<KEY, Integer>
        implements IntSupplier {

    private final Supplier<KEY> keySupplier;
    private final IntSupplier supplier;

    IntSupplierMemoizer(
            final ConcurrentMap<KEY, Integer> cache,
            final Supplier<KEY> keySupplier,
            final IntSupplier supplier) {
        super(cache);
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a key supplier, might just be 'MemoizationDefaults.staticKey()'.");
        this.supplier = requireNonNull(supplier,
                "Cannot memoize a NULL Supplier - provide an actual Supplier to fix this.");
    }

    @Override
    public int getAsInt() {
        return computeIfAbsent(keySupplier.get(), input -> supplier.getAsInt());
    }

}
