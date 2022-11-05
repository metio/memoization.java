/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

final class GuavaCacheBasedBooleanSupplierMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Boolean>
        implements BooleanSupplier {

    private final Supplier<KEY> keySupplier;
    private final BooleanSupplier supplier;

    GuavaCacheBasedBooleanSupplierMemoizer(
            final Cache<KEY, Boolean> cache,
            final Supplier<KEY> keySupplier,
            final BooleanSupplier supplier) {
        super(cache);
        this.keySupplier = keySupplier;
        this.supplier = supplier;
    }

    @Override
    public boolean getAsBoolean() {
        return get(keySupplier.get(), givenKey -> supplier.getAsBoolean());
    }

}
