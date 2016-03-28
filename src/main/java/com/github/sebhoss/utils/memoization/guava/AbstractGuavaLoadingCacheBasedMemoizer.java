package com.github.sebhoss.utils.memoization.guava;

import java.util.concurrent.ExecutionException;

import com.github.sebhoss.utils.memoization.shared.MemoizationException;
import com.google.common.cache.LoadingCache;

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
