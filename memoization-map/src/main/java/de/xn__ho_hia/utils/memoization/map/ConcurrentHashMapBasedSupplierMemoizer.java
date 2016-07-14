package de.xn__ho_hia.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.Supplier;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedSupplierMemoizer<KEY, VALUE>
        extends ConcurrentHashMapBasedMemoizer<KEY, VALUE>
        implements Supplier<VALUE> {

    private final Supplier<KEY>   keySupplier;
    private final Supplier<VALUE> supplier;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedSupplierMemoizer(
            final Map<KEY, VALUE> preComputedValues,
            final Supplier<KEY> keySupplier,
            final Supplier<VALUE> supplier) {
        super(preComputedValues);
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
