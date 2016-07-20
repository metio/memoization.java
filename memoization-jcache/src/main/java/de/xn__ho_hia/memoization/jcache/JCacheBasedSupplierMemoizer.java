package de.xn__ho_hia.memoization.jcache;

import java.util.function.Supplier;

import javax.cache.Cache;

final class JCacheBasedSupplierMemoizer<KEY, VALUE>
        extends AbstractJCacheBasedMemoizer<KEY, VALUE>
        implements Supplier<VALUE> {

    private final Supplier<KEY> keySupplier;

    JCacheBasedSupplierMemoizer(
            final Cache<KEY, VALUE> cache,
            final Supplier<KEY> keySupplier) {
        super(cache);
        this.keySupplier = keySupplier;
    }

    @Override
    public VALUE get() {
        return get(keySupplier.get());
    }

}
