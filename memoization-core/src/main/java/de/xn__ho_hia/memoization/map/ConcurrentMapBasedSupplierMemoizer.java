/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentMapBasedSupplierMemoizer<KEY, VALUE>
        extends ConcurrentMapBasedMemoizer<KEY, VALUE>
        implements Supplier<VALUE> {

    private final Supplier<KEY>   keySupplier;
    private final Supplier<VALUE> supplier;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentMapBasedSupplierMemoizer(
            final ConcurrentMap<KEY, VALUE> cache,
            final Supplier<KEY> keySupplier,
            final Supplier<VALUE> supplier) {
        super(cache);
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a key function, might just be 'MemoizationDefaults.defaultKeySupplier()'.");
        this.supplier = requireNonNull(supplier,
                "Cannot memoize a NULL Supplier - provide an actual Supplier to fix this.");
    }

    @Override
    public VALUE get() {
        return computeIfAbsent(keySupplier.get(), input -> supplier.get());
    }

}
