package com.github.sebhoss.utils.memoization.guava;

import java.util.function.Function;

import com.google.common.cache.LoadingCache;

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
