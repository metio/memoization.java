package com.github.sebhoss.utils.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.Supplier;

final class ConcurrentHashMapBasedSupplierMemoizer<KEY, VALUE>
        extends AbstractConcurrentHashMapBasedMemoizer<KEY, VALUE>
        implements Supplier<VALUE> {

    private final Supplier<KEY>   keySupplier;
    private final Supplier<VALUE> supplier;

    @SuppressWarnings("nls")
    ConcurrentHashMapBasedSupplierMemoizer(
            final Map<KEY, VALUE> preComputedValues,
            final Supplier<KEY> keySupplier,
            final Supplier<VALUE> supplier) {
        super(requireNonNull(preComputedValues,
                "Provide an empty map instead of NULL in case you don't have any precomputed values."));
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a constant key supplier like this '() -> \"myValue\"'.");
        this.supplier = requireNonNull(supplier,
                "Cannot memoize a NULL supplier - provide an actual supplier to fix this.");
    }

    @Override
    public VALUE get() {
        return computeIfAbsent(keySupplier.get(), input -> supplier.get());
    }

}
