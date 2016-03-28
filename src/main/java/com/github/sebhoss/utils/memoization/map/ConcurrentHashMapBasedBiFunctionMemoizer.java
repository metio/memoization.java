package com.github.sebhoss.utils.memoization.map;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.github.sebhoss.utils.memoization.shared.MemoizingBiFunction;

final class ConcurrentHashMapBasedBiFunctionMemoizer<FIRST, SECOND, KEY, VALUE> extends AbstractConcurrentHashMapBasedMemoizer<KEY, VALUE>
		implements MemoizingBiFunction<FIRST, SECOND, KEY, VALUE> {

	private final BiFunction<FIRST, SECOND, KEY> keyFunction;
	private final BiFunction<FIRST, SECOND, VALUE> biFunction;

	ConcurrentHashMapBasedBiFunctionMemoizer(
			final Map<KEY, VALUE> preComputedValues,
			final BiFunction<FIRST, SECOND, KEY> keyFunction,
			final BiFunction<FIRST, SECOND, VALUE> biFunction) {
		super(preComputedValues);
		this.keyFunction = keyFunction;
		this.biFunction = biFunction;
	}

	@Override
	public BiFunction<FIRST, SECOND, KEY> getKeyFunction() {
		return keyFunction;
	}

	@Override
	public BiFunction<FIRST, SECOND, VALUE> getBiFunction() {
		return biFunction;
	}

	@Override
	public BiFunction<KEY, Function<KEY, VALUE>, VALUE> getMemoizingFunction() {
		return this::computeIfAbsent;
	}

}
