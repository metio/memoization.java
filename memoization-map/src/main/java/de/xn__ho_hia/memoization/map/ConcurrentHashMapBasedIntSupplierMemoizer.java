package de.xn__ho_hia.memoization.map;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import de.xn__ho_hia.quality.suppression.CompilerWarnings;

final class ConcurrentHashMapBasedIntSupplierMemoizer<KEY>
        extends ConcurrentHashMapBasedMemoizer<KEY, Integer>
        implements IntSupplier {

    private final Supplier<KEY> keySupplier;
    private final IntSupplier   supplier;

    @SuppressWarnings(CompilerWarnings.NLS)
    ConcurrentHashMapBasedIntSupplierMemoizer(
            final Map<KEY, Integer> preComputedValues,
            final Supplier<KEY> keySupplier,
            final IntSupplier supplier) {
        super(preComputedValues);
        this.keySupplier = requireNonNull(keySupplier,
                "Provide a key function, might just be 'MemoizationDefaults.defaultKeySupplier()'.");
        this.supplier = requireNonNull(supplier,
                "Cannot memoize a NULL Supplier - provide an actual Supplier to fix this.");
    }

    @Override
    public int getAsInt() {
        return computeIfAbsent(keySupplier.get(), input -> Integer.valueOf(supplier.getAsInt())).intValue();
    }

}
