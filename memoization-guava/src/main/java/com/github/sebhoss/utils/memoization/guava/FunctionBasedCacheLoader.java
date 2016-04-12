package com.github.sebhoss.utils.memoization.guava;

import java.util.function.Function;

import com.google.common.cache.CacheLoader;

final class FunctionBasedCacheLoader<KEY, VALUE> extends CacheLoader<KEY, VALUE> {

	private final Function<KEY, VALUE> function;

	FunctionBasedCacheLoader(final Function<KEY, VALUE> function) {
		this.function = function;
	}

	@Override
	public VALUE load(final KEY key) throws Exception {
		return function.apply(key);
	}

}
