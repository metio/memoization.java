package de.xn__ho_hia.memoization.guava;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;

import com.google.common.cache.Cache;

import de.xn__ho_hia.memoization.shared.MemoizationException;

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
