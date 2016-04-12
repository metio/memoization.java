package com.github.sebhoss.utils.memoization.jsr107;

import java.util.function.Function;

import javax.cache.Cache;

import com.github.sebhoss.utils.memoization.shared.MemoizationException;

abstract class AbstractJCacheBasedMemoizer<KEY, VALUE> {

	private final Cache<KEY, VALUE> cache;

	AbstractJCacheBasedMemoizer(final Cache<KEY, VALUE> cache) {
		this.cache = cache;
	}

	protected final VALUE get(final KEY key) {
		try {
			return cache.get(key);
		} catch (final Throwable throwable) {
			throw new MemoizationException(throwable);
		}
	}

	protected final VALUE putIfAbsent(final KEY key, Function<KEY, VALUE> mappingFunction) {
		try {
			if (!cache.containsKey(key)) {
				cache.put(key, mappingFunction.apply(key));
			}
			return cache.get(key);
		} catch (final Throwable throwable) {
			throw new MemoizationException(throwable);
		}
	}

}
