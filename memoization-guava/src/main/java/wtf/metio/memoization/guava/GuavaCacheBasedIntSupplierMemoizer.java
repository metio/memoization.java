/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

final class GuavaCacheBasedIntSupplierMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Integer>
        implements IntSupplier {

    private final Supplier<KEY> keySupplier;
    private final IntSupplier supplier;

    GuavaCacheBasedIntSupplierMemoizer(
            final Cache<KEY, Integer> cache,
            final Supplier<KEY> keySupplier,
            final IntSupplier supplier) {
        super(cache);
        this.keySupplier = keySupplier;
        this.supplier = supplier;
    }

    @Override
    public int getAsInt() {
        return get(keySupplier.get(), givenKey -> supplier.getAsInt());
    }

}
