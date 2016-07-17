package de.xn__ho_hia.memoization.guava;

import com.google.common.cache.LoadingCache;

import java.util.function.Supplier;

final class GuavaCacheBasedSupplierMemoizer<KEY, VALUE> extends AbstractGuavaLoadingCacheBasedMemoizer<KEY, VALUE>
        implements Supplier<VALUE> {

    private final Supplier<KEY> keySupplier;

    GuavaCacheBasedSupplierMemoizer(
            final LoadingCache<KEY, VALUE> cache,
            final Supplier<KEY> keySupplier) {
        super(cache);
        this.keySupplier = keySupplier;
    }

    @Override
    public VALUE get() {
        return get(keySupplier.get());
    }

}
