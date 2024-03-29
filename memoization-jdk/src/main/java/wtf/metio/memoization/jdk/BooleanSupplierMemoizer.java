/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.jdk;

import wtf.metio.memoization.core.AbstractMemoizer;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

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
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a key supplier, might just be 'MemoizationDefaults.staticKey()'.");
        this.supplier = requireNonNull(supplier,
                "Cannot memoize a NULL BooleanSupplier - provide an actual BooleanSupplier to fix this.");
    }

    @Override
    public boolean getAsBoolean() {
        return computeIfAbsent(keySupplier.get(), input -> supplier.getAsBoolean());
    }

}
