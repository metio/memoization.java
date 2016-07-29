/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.jcache;

import java.util.function.LongSupplier;
import java.util.function.Supplier;

import javax.cache.Cache;

final class JCacheBasedLongSupplierMemoizer<KEY>
        extends AbstractJCacheBasedMemoizer<KEY, Long>
        implements LongSupplier {

    private final Supplier<KEY> keySupplier;
    private final LongSupplier  supplier;

    JCacheBasedLongSupplierMemoizer(
            final Cache<KEY, Long> cache,
            final Supplier<KEY> keySupplier,
            final LongSupplier supplier) {
        super(cache);
        this.keySupplier = keySupplier;
        this.supplier = supplier;
    }

    @Override
    public long getAsLong() {
        return invoke(keySupplier.get(), key -> Long.valueOf(supplier.getAsLong())).longValue();
    }

}
