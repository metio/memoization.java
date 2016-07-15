/*
 * This file is part of memoization.java. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of memoization.java,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedDoubleSupplierMemoizer<KEY>
        extends ConcurrentHashMapBasedMemoizer<KEY, Double>
        implements DoubleSupplier {

    private final Supplier<KEY>  keySupplier;
    private final DoubleSupplier supplier;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedDoubleSupplierMemoizer(
            final Map<KEY, Double> preComputedValues,
            final Supplier<KEY> keySupplier,
            final DoubleSupplier supplier) {
        super(preComputedValues);
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a key function, might just be 'MemoizationDefaults.defaultKeySupplier()'.");
        this.supplier = requireNonNull(supplier,
                "Cannot memoize a NULL Supplier - provide an actual Supplier to fix this.");
    }

    @Override
    public double getAsDouble() {
        return computeIfAbsent(keySupplier.get(), input -> Double.valueOf(supplier.getAsDouble())).doubleValue();
    }

}
