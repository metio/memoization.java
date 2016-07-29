/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import com.google.common.cache.Cache;

final class GuavaCacheBasedBooleanSupplierMemoizer<KEY>
        extends AbstractGuavaCacheBasedMemoizer<KEY, Boolean>
        implements BooleanSupplier {

    private final Supplier<KEY>   keySupplier;
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
        return get(keySupplier.get(), givenKey -> Boolean.valueOf(supplier.getAsBoolean())).booleanValue();
    }

}
