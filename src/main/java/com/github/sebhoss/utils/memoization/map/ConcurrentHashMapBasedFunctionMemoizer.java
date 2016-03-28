package com.github.sebhoss.utils.memoization.map;

import java.util.Map;
import java.util.function.Function;

final class ConcurrentHashMapBasedFunctionMemoizer<KEY, VALUE> extends AbstractConcurrentHashMapBasedMemoizer<KEY, VALUE>
		implements Function<KEY, VALUE> {

	private final Function<KEY, VALUE> function;

	ConcurrentHashMapBasedFunctionMemoizer(
			final Map<KEY, VALUE> preComputedValues,
			final Function<KEY, VALUE> function) {
		super(preComputedValues);
		this.function = function;
	}

	@Override
	public VALUE apply(final KEY input) {
		return computeIfAbsent(input, function);
	}

}
