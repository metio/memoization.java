/*
 * SPDX-FileCopyrightText: The memoization.java Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.memoization.guava;

import com.google.common.cache.Cache;

import java.util.function.Supplier;

final class GuavaCacheBasedSupplierMemoizer<KEY, OUTPUT>
        extends AbstractGuavaCacheBasedMemoizer<KEY, OUTPUT>
        implements Supplier<OUTPUT> {

    private final Supplier<KEY> keySupplier;
    private final Supplier<OUTPUT> supplier;

    GuavaCacheBasedSupplierMemoizer(
            final Cache<KEY, OUTPUT> cache,
            final Supplier<KEY> keySupplier,
            final Supplier<OUTPUT> supplier) {
        super(cache);
        this.keySupplier = keySupplier;
        this.supplier = supplier;
    }

    @Override
    public OUTPUT get() {
        return get(keySupplier.get(), givenKey -> supplier.get());
    }

}
