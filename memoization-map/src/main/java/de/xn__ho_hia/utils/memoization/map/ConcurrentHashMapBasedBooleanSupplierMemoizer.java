package de.xn__ho_hia.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

final class ConcurrentHashMapBasedBooleanSupplierMemoizer<KEY>
        extends ConcurrentHashMapBasedMemoizer<KEY, Boolean>
        implements BooleanSupplier {

    private final Supplier<KEY>   keySupplier;
    private final BooleanSupplier supplier;

    @SuppressWarnings("nls")
    ConcurrentHashMapBasedBooleanSupplierMemoizer(
            final Map<KEY, Boolean> preComputedValues,
            final Supplier<KEY> keySupplier,
            final BooleanSupplier supplier) {
        super(preComputedValues);
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a key function, might just be 'MemoizationDefaults.defaultKeySupplier()'.");
        this.supplier = requireNonNull(supplier,
                "Cannot memoize a NULL Supplier - provide an actual Supplier to fix this.");
    }

    @Override
    public boolean getAsBoolean() {
        return computeIfAbsent(keySupplier.get(), input -> Boolean.valueOf(supplier.getAsBoolean())).booleanValue();
    }

}
