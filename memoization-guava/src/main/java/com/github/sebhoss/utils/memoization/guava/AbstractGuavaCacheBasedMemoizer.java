package com.github.sebhoss.utils.memoization.guava;

import com.github.sebhoss.utils.memoization.shared.MemoizationException;
import com.google.common.cache.Cache;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;

abstract class AbstractGuavaCacheBasedMemoizer<KEY, VALUE> {

    private final Cache<KEY, VALUE> cache;

    AbstractGuavaCacheBasedMemoizer(final Cache<KEY, VALUE> cache) {
        this.cache = cache;
    }

    protected final VALUE get(final KEY key, final Function<KEY, VALUE> function) {
        try {
            return cache.get(key, () -> function.apply(key));
        } catch (final ExecutionException exception) {
            throw new MemoizationException(exception);
        }
    }

}
