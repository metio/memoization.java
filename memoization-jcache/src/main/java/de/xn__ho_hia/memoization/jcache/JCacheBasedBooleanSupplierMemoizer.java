/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import javax.cache.Cache;

final class JCacheBasedBooleanSupplierMemoizer<KEY>
        extends AbstractJCacheBasedMemoizer<KEY, Boolean>
        implements BooleanSupplier {

    private final Supplier<KEY>   keySupplier;
    private final BooleanSupplier supplier;

    JCacheBasedBooleanSupplierMemoizer(
            final Cache<KEY, Boolean> cache,
            final Supplier<KEY> keySupplier,
            final BooleanSupplier supplier) {
        super(cache);
        this.keySupplier = keySupplier;
        this.supplier = supplier;
    }

    @Override
    public boolean getAsBoolean() {
        return invoke(keySupplier.get(), key -> Boolean.valueOf(supplier.getAsBoolean())).booleanValue();
    }

}
