package com.github.sebhoss.utils.memoization.jsr107;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.cache.Cache;

import com.github.sebhoss.utils.memoization.shared.MemoizingConsumer;

final class JCacheBasedConsumerMemoizer<KEY, VALUE> extends AbstractJCacheBasedMemoizer<KEY, VALUE>
		implements MemoizingConsumer<KEY, VALUE>{

	private final Function<VALUE, KEY> keyFunction;
	private final Consumer<VALUE> consumer;

	JCacheBasedConsumerMemoizer(
			final Cache<KEY, VALUE> cache,
			final Function<VALUE, KEY> keyFunction,
			final Consumer<VALUE> consumer) {
		super(cache);
		this.keyFunction = keyFunction;
		this.consumer = consumer;
	}

	@Override
	public BiFunction<KEY, Function<KEY, VALUE>, VALUE> getMemoizingFunction() {
		return this::putIfAbsent;
	}

	@Override
	public final Function<VALUE, KEY> getKeyFunction() {
		return keyFunction;
	}

	@Override
	public final Consumer<VALUE> getConsumer() {
		return consumer;
	}

}
