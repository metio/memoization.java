package de.xn__ho_hia.memoization.jsr107;

import javax.cache.Cache;

import de.xn__ho_hia.memoization.shared.MemoizationException;

abstract class AbstractJCacheBasedMemoizer<KEY, VALUE> {

    private final Cache<KEY, VALUE> cache;

    AbstractJCacheBasedMemoizer(final Cache<KEY, VALUE> cache) {
        this.cache = cache;
    }

    protected final VALUE get(final KEY key) {
        try {
            return cache.get(key);
        } catch (final RuntimeException exception) {
            throw new MemoizationException(exception);
        }
    }

}
