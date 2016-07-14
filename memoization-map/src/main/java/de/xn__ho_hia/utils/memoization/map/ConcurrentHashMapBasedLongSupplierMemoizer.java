package de.xn__ho_hia.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedLongSupplierMemoizer<KEY>
        extends ConcurrentHashMapBasedMemoizer<KEY, Long>
        implements LongSupplier {

    private final Supplier<KEY> keySupplier;
    private final LongSupplier  supplier;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedLongSupplierMemoizer(
            final Map<KEY, Long> preComputedValues,
            final Supplier<KEY> keySupplier,
            final LongSupplier supplier) {
        super(preComputedValues);
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a key function, might just be 'MemoizationDefaults.defaultKeySupplier()'.");
        this.supplier = requireNonNull(supplier,
                "Cannot memoize a NULL Supplier - provide an actual Supplier to fix this.");
    }

    @Override
    public long getAsLong() {
        return computeIfAbsent(keySupplier.get(), input -> Long.valueOf(supplier.getAsLong())).longValue();
    }

}
