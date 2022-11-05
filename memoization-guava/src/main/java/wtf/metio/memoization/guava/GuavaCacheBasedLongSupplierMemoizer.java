/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.LongSupplier;
import java.util.function.Supplier;

final class GuavaCacheBasedLongSupplierMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Long>
        implements LongSupplier {

    private final Supplier<KEY> keySupplier;
    private final LongSupplier supplier;

    GuavaCacheBasedLongSupplierMemoizer(
            final Cache<KEY, Long> cache,
            final Supplier<KEY> keySupplier,
            final LongSupplier supplier) {
        super(cache);
        this.keySupplier = keySupplier;
        this.supplier = supplier;
    }

    @Override
    public long getAsLong() {
        return get(keySupplier.get(), givenKey -> supplier.getAsLong());
    }

}
