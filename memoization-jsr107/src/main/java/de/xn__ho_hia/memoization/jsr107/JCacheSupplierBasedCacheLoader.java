package de.xn__ho_hia.memoization.jsr107;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;

final class JCacheSupplierBasedCacheLoader<KEY, VALUE> implements CacheLoader<KEY, VALUE> {

    private final Supplier<VALUE> supplier;

    JCacheSupplierBasedCacheLoader(final Supplier<VALUE> supplier) {
        this.supplier = supplier;
    }

    @Override
    public VALUE load(final KEY key) throws CacheLoaderException {
        return supplier.get();
    }

    @Override
    public Map<KEY, VALUE> loadAll(final Iterable<? extends KEY> keys) throws CacheLoaderException {
        return StreamSupport.stream(keys.spliterator(), false)
                .collect(Collectors.toMap(Function.identity(), this::load));
    }

}
