/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

final class GuavaCacheBasedDoubleSupplierMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Double>
        implements DoubleSupplier {

    private final Supplier<KEY> keySupplier;
    private final DoubleSupplier supplier;

    GuavaCacheBasedDoubleSupplierMemoizer(
            final Cache<KEY, Double> cache,
            final Supplier<KEY> keySupplier,
            final DoubleSupplier supplier) {
        super(cache);
        this.keySupplier = keySupplier;
        this.supplier = supplier;
    }

    @Override
    public double getAsDouble() {
        return get(keySupplier.get(), givenKey -> supplier.getAsDouble());
    }

}
