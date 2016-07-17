package de.xn__ho_hia.memoization.guava;

import java.util.concurrent.ExecutionException;

import com.google.common.cache.LoadingCache;

import de.xn__ho_hia.memoization.shared.MemoizationException;

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
