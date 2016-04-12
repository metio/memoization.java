package com.github.sebhoss.utils.memoization.guava;

import java.util.function.Supplier;

import com.google.common.cache.CacheLoader;

final class SupplierBasedCacheLoader<KEY, VALUE> extends CacheLoader<KEY, VALUE> {

	private final Supplier<VALUE> supplier;

	SupplierBasedCacheLoader(final Supplier<VALUE> supplier) {
		this.supplier = supplier;
	}

	@Override
	public VALUE load(final KEY key) throws Exception {
		return supplier.get();
	}

}
