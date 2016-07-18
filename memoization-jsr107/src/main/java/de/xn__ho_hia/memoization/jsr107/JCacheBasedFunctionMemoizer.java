package de.xn__ho_hia.memoization.jsr107;

import java.util.function.Function;

import javax.cache.Cache;

final class JCacheBasedFunctionMemoizer<KEY, VALUE>
        extends AbstractJCacheBasedMemoizer<KEY, VALUE>
        implements Function<KEY, VALUE> {

    JCacheBasedFunctionMemoizer(final Cache<KEY, VALUE> cache) {
        super(cache);
    }

    @Override
    public VALUE apply(final KEY input) {
        return get(input);
    }

}
