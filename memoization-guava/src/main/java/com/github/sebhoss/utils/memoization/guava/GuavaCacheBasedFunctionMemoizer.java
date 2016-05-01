package com.github.sebhoss.utils.memoization.guava;

import com.google.common.cache.LoadingCache;

import java.util.function.Function;

final class GuavaCacheBasedFunctionMemoizer<KEY, VALUE> extends AbstractGuavaLoadingCacheBasedMemoizer<KEY, VALUE>
        implements Function<KEY, VALUE> {

    GuavaCacheBasedFunctionMemoizer(final LoadingCache<KEY, VALUE> cache) {
        super(cache);
    }

    @Override
    public VALUE apply(final KEY input) {
        return get(input);
    }

}
