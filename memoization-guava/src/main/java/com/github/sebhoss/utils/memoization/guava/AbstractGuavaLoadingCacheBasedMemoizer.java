package com.github.sebhoss.utils.memoization.guava;

import com.github.sebhoss.utils.memoization.shared.MemoizationException;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

abstract class AbstractGuavaLoadingCacheBasedMemoizer<KEY, VALUE> {

    private final LoadingCache<KEY, VALUE> cache;

    AbstractGuavaLoadingCacheBasedMemoizer(final LoadingCache<KEY, VALUE> cache) {
        this.cache = cache;
    }

    protected final VALUE get(final KEY key) {
        try {
            return cache.get(key);
        } catch (final ExecutionException exception) {
            throw new MemoizationException(exception);
        }
    }

}
