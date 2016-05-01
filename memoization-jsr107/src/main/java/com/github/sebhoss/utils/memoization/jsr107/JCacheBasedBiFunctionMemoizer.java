package com.github.sebhoss.utils.memoization.jsr107;

import java.util.function.BiFunction;
import java.util.function.Function;

import javax.cache.Cache;

import com.github.sebhoss.utils.memoization.shared.MemoizingBiFunction;

final class JCacheBasedBiFunctionMemoizer<FIRST, SECOND, KEY, VALUE> extends AbstractJCacheBasedMemoizer<KEY, VALUE>
		implements MemoizingBiFunction<FIRST, SECOND, KEY, VALUE> {

	private final BiFunction<FIRST, SECOND, KEY> keyFunction;
	private final BiFunction<FIRST, SECOND, VALUE> biFunction;

	JCacheBasedBiFunctionMemoizer(
			final Cache<KEY, VALUE> preComputedValues,
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
		return this::putIfAbsent;
	}

}
