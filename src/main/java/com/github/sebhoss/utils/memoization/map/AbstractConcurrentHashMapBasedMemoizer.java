package com.github.sebhoss.utils.memoization.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

abstract class AbstractConcurrentHashMapBasedMemoizer<KEY, VALUE> {

	private final Map<KEY, VALUE> cache;

	AbstractConcurrentHashMapBasedMemoizer(final Map<KEY, VALUE> preComputedValues) {
		this.cache = new ConcurrentHashMap<>(preComputedValues);
	}

	protected final VALUE computeIfAbsent(final KEY key, final Function<KEY, VALUE> mappingFunction) {
		return cache.computeIfAbsent(key, mappingFunction);
	}

}
