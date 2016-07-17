/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.guava;

import java.util.Objects;
import java.util.function.Supplier;

import com.google.common.cache.CacheLoader;

final class SupplierBasedCacheLoader<KEY, VALUE> extends CacheLoader<KEY, VALUE> {

    private final Supplier<VALUE> supplier;

    SupplierBasedCacheLoader(final Supplier<VALUE> supplier) {
        this.supplier = Objects.requireNonNull(supplier, "Provide a supplier to load values into the cache!"); //$NON-NLS-1$
    }

    @Override
    public VALUE load(final KEY key) throws Exception {
        return supplier.get();
    }

}
