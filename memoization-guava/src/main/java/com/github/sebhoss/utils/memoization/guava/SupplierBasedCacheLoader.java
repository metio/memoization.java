package com.github.sebhoss.utils.memoization.guava;

import com.google.common.cache.CacheLoader;

import java.util.Objects;
import java.util.function.Supplier;

final class SupplierBasedCacheLoader<KEY, VALUE> extends CacheLoader<KEY, VALUE> {

    private final Supplier<VALUE> supplier;

    SupplierBasedCacheLoader(final Supplier<VALUE> supplier) {
        this.supplier = Objects.requireNonNull(supplier, "Provide a supplier to load values into the cache!");
    }

    @Override
    public VALUE load(final KEY key) throws Exception {
        return supplier.get();
    }

}
